import { Component } from '@angular/core';
import { Word } from '../word';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { WordService } from '../word.service';

@Component({
  selector: 'app-update-word',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './update-word.component.html',
  styleUrl: './update-word.component.css'
})
export class UpdateWordComponent {
  wordParam!: string;
  word: Word = new Word;
  constructor(private service: WordService, private route: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    this.wordParam = this.route.snapshot.params['word']
    this.service.getSingleWord(this.wordParam).subscribe(data => {
      this.word = data;
    }, error => console.log(error));
  }

  updateWord(word: Word) {

  }

  onSubmit() {
    console.log(this.word);
    this.service.updateWord(this.word).subscribe( data =>{
      this.goToWordList();
    }
    , error => console.log(error));
  }

  goToWordList(){
    this.router.navigate(['/words']);
  }
}
