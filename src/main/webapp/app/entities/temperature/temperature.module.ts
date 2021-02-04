import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TestSharedModule } from 'app/shared/shared.module';
import { TemperatureComponent } from './temperature.component';
import { TemperatureDetailComponent } from './temperature-detail.component';
import { TemperatureUpdateComponent } from './temperature-update.component';
import { TemperatureDeleteDialogComponent } from './temperature-delete-dialog.component';
import { temperatureRoute } from './temperature.route';

@NgModule({
  imports: [TestSharedModule, RouterModule.forChild(temperatureRoute)],
  declarations: [TemperatureComponent, TemperatureDetailComponent, TemperatureUpdateComponent, TemperatureDeleteDialogComponent],
  entryComponents: [TemperatureDeleteDialogComponent],
})
export class TestTemperatureModule {}
