import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RefillStatusComponent } from './refill-status.component';

describe('RefillStatusComponent', () => {
  let component: RefillStatusComponent;
  let fixture: ComponentFixture<RefillStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RefillStatusComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RefillStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
