import { TestBed } from '@angular/core/testing';

import { RefillStatusService } from './refill-status.service';

describe('RefillStatusService', () => {
  let service: RefillStatusService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RefillStatusService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
