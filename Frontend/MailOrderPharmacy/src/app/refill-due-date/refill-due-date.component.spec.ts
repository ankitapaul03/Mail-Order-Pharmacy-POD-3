import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RefillDueDateComponent } from './refill-due-date.component';

describe('RefillDueDateComponent', () => {
  let component: RefillDueDateComponent;
  let fixture: ComponentFixture<RefillDueDateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RefillDueDateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RefillDueDateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
