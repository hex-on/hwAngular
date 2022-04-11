import { Pipe, PipeTransform } from '@angular/core';
@Pipe({
  name: 'searchfilter'
})
export class SearchfilterPipe implements PipeTransform {

  transform(value: any, searchInput: string) {
    if (value.length === 0 || searchInput === ''){
      return value;
    }

    const employees = [];
    for (const employee of value){
      if (employee['firstName'].toLowerCase().includes(searchInput.toLowerCase())) {
        employees.push(employee);
      }else if (employee['lastName'].toLowerCase().includes(searchInput.toLowerCase())) {
        employees.push(employee);
      }else if (employee['email'].toLowerCase().includes(searchInput.toLowerCase())) {
        employees.push(employee);
      }
    }
    return employees;
  }

}
