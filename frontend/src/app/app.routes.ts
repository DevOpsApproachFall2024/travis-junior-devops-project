import { Routes } from '@angular/router';
import { TonyComponent } from './tony.component';
import { HomeComponent } from './home.component';
import { FibonacciComponent } from './fibonacci.component';

export const routes: Routes = [
  {path: '', component: HomeComponent},
  { path: 'tony', component: TonyComponent },
  { path: 'fibonacci', component: FibonacciComponent }
];
