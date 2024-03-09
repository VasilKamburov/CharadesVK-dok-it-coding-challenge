import { Routes } from '@angular/router';
import { WordListComponent } from './word-list/word-list.component';
import { CreateWordComponent } from './create-word/create-word.component';
import { UpdateWordComponent } from './update-word/update-word.component';
import { RandomWordComponent } from './random-word/random-word.component';

export const routes: Routes = [
    {path: 'words', component: WordListComponent},
    {path: 'create-word', component: CreateWordComponent},
    {path: '', redirectTo: 'words', pathMatch: 'full'},
    {path: 'update-word/:word', component: UpdateWordComponent},
    {path: 'random', component: RandomWordComponent},
    {path: '**', redirectTo: 'words', pathMatch: 'full'}
];
