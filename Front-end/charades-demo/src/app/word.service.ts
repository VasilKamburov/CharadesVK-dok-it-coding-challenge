import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Word } from './word';
import { UUID } from 'crypto';

@Injectable({
  providedIn: 'root'
})
export class WordService {
  
  baseUrl: string = "http://localhost:8080/api/v1/words";
  searchAppend: string = "/search?word="
  updateAppend: string = "/modify-description"
  randomAppend: string = "/random"

  constructor(private httpClient: HttpClient) { }

  getWordList(): Observable<Word[]> {
    return this.httpClient.get<Word[]>(`${this.baseUrl}`)
  }

  createWord(word: Word): Observable<Object> {
    return this.httpClient.post(`${this.baseUrl}/add`, word);
  }

  getSingleWord(word: string): Observable<Word> {
    return this.httpClient.get<Word>(`${this.baseUrl}${this.searchAppend}${word}`)
  }

  getRandomWord(): Observable<Word> {
    return this.httpClient.get<Word>(`${this.baseUrl}${this.randomAppend}`)
  }

  updateWord(word: Word): Observable<Object> {
    return this.httpClient.put(`${this.baseUrl}${this.updateAppend}`, word);
  }

  deleteWord(id: UUID): Observable<Object> {
    return this.httpClient.delete(`${this.baseUrl}/${id}`)
  }
}
