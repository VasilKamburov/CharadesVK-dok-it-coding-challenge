import { Component } from '@angular/core';
import { NgFor } from '@angular/common';
import { Word } from '../word';
import { Router } from '@angular/router';
import { UUID } from 'crypto';
import { WordService } from '../word.service';

@Component({
  selector: 'app-word-list',
  standalone: true,
  imports: [NgFor],
  templateUrl: './word-list.component.html',
  styleUrl: './word-list.component.css'
})
export class WordListComponent {
  words: Word[] = [];
  title: string = "Word list";

  constructor(private appService: WordService, private router: Router) {}

  ngOnInit() {
    this.getWordList();
  }

  getWordList() {
    this.appService.getWordList().subscribe(data => {
      this.words = data
    });
  }

  updateWord(word: string) {
    this.router.navigate(['update-word', word])
  }

  deleteWord(id: UUID) {
    this.appService.deleteWord(id).subscribe(data => {
      console.log(data);
      this.getWordList();
    })
  }
}
