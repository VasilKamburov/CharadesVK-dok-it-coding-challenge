import { Component } from '@angular/core';
import { Word } from '../word';
import { WordService } from '../word.service';

@Component({
  selector: 'app-random-word',
  standalone: true,
  imports: [],
  templateUrl: './random-word.component.html',
  styleUrl: './random-word.component.css'
})
export class RandomWordComponent {
  word: Word = new Word();

  constructor(private service: WordService) {}

  ngOnInit() {
    this.getRandomWord();
  }

  getRandomWord() {
    this.service.getRandomWord().subscribe(data => {
      this.word = data
    }, error => console.log(error));
  }

  getNewWord() {
    this.getRandomWord();
  }
}
