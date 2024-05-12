import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PermisionDeniedComponent } from './permision-denied.component';

describe('PermisionDeniedComponent', () => {
  let component: PermisionDeniedComponent;
  let fixture: ComponentFixture<PermisionDeniedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PermisionDeniedComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PermisionDeniedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
