import { Component } from '@angular/core';
import { Word } from '../word';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { WordService } from '../word.service';

@Component({
  selector: 'app-create-word',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './create-word.component.html',
  styleUrl: './create-word.component.css'
})
export class CreateWordComponent {
  word: Word = new Word();

  constructor(private service: WordService, private router: Router) {}

  ngOnInit() {}

  saveEmployee(){
    this.service.createWord(this.word).subscribe( data =>{
      console.log(data);
      this.goToEmployeeList();
    },
    error => console.log(error));
  }

  goToEmployeeList(){
    this.router.navigate(['/words']);
  }

  onSubmit() {
    console.log(this.word);
    this.saveEmployee();
  }
}
