FROM node:22-alpine AS dependencies

WORKDIR /app

COPY package.json package-lock.json ./

RUN npm clean-install

FROM node:22-alpine AS base
WORKDIR /app


COPY --from=dependencies /app/node_modules ./node_modules
COPY . .

FROM base AS run_tests

RUN apk add chromium

ENV CHROME_BIN=/usr/bin/chromium-browser

RUN npm run test-headless

FROM base AS build-app

# Dummy dependancy to ensure tests pass before building
COPY --from=run_tests /tmp/dummy* /tmp 
COPY ./src/environments/environment.ts ./src/environments/environment.prod.ts

ARG API_URL
ENV API_URL=${API_URL}
RUN sed -i 's|%#API_URL#%|'"$API_URL"'|g' ./src/environments/environment.prod.ts && npm run build:production


FROM nginx:1.27.3-alpine

EXPOSE 3000

COPY --from=build-app /app/dist/frontend/browser /usr/share/nginx/html



