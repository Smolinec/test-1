import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TestSharedModule } from 'app/shared/shared.module';
import { DeviceConfigurationComponent } from './device-configuration.component';
import { DeviceConfigurationDetailComponent } from './device-configuration-detail.component';
import { DeviceConfigurationUpdateComponent } from './device-configuration-update.component';
import { DeviceConfigurationDeleteDialogComponent } from './device-configuration-delete-dialog.component';
import { deviceConfigurationRoute } from './device-configuration.route';

@NgModule({
  imports: [TestSharedModule, RouterModule.forChild(deviceConfigurationRoute)],
  declarations: [
    DeviceConfigurationComponent,
    DeviceConfigurationDetailComponent,
    DeviceConfigurationUpdateComponent,
    DeviceConfigurationDeleteDialogComponent,
  ],
  entryComponents: [DeviceConfigurationDeleteDialogComponent],
})
export class TestDeviceConfigurationModule {}
