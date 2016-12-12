import { Component} from '@angular/core';

@Component({
  selector: 'my-app',
  template: `<h1><p class="text-warning">Hello {{name}}.. Here is Angular!!</p></h1>`,
})
export class AppComponent  { name = 'World';}
