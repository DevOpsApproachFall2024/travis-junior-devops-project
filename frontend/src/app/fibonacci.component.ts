import {Component} from '@angular/core';
import {ReactiveFormsModule, FormControl, Validators} from '@angular/forms';
import { ApiService } from './api.service';
import { CommonModule, NgIf } from '@angular/common';

@Component({
  selector: 'app-fibonacci',
  template: `
    Enter fibonacci position: <input type="number" [formControl]="fibonacciSequenceControl">
    <button (click)="onSubmit()">Submit</button>
    <div *ngIf="fibonacciSequenceControl.invalid && (fibonacciSequenceControl.dirty || fibonacciSequenceControl.touched)">
      <div *ngIf="fibonacciSequenceControl.errors?.['required']">Position is required</div>
      <div *ngIf="fibonacciSequenceControl.errors?.['min']">Position must be greater than or equal to 0</div>
    </div>
    <div *ngIf="result !== null">
        Fibonacci number at position {{ fibonacciSequenceControl.value }} is {{ result }}
    </div>
  `,
  styles: [`
    input {
      margin-right: 10px;
      border: 2px solid green;
      padding: 5px;
      border-radius: 5px;
    }
    button {
      margin-top: 10px;
      background-color: red;
      color: white;
      border: none;
      padding: 10px;
      border-radius: 5px;
      cursor: pointer;
    }
    button:hover {
      background-color: darkred;
    }
    div {
      margin-top: 10px;
      color: green;
    }
    .error {
      color: red;
    }
  `],
  imports: [ReactiveFormsModule, NgIf, CommonModule],
  standalone: true,
  providers: [ApiService]
})
export class FibonacciComponent {
  fibonacciSequenceControl = new FormControl(0, {validators: [Validators.required, Validators.min(0)], updateOn: 'change'});
  result: number | null = null;

  constructor(private readonly apiService: ApiService) {}

  onSubmit() {
    if (this.fibonacciSequenceControl.valid && this.fibonacciSequenceControl.value !== null) {
      const position = this.fibonacciSequenceControl.value;
      // eslint-disable-next-line @typescript-eslint/no-explicit-any
      this.apiService.getFibonacci(position).subscribe((response: any) => {
        this.result = response.result;
      });
    }
  }
}