import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchDrugComponent } from './search-drug.component';

describe('SearchDrugComponent', () => {
  let component: SearchDrugComponent;
  let fixture: ComponentFixture<SearchDrugComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchDrugComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchDrugComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
