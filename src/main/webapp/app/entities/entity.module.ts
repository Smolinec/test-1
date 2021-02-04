import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'place',
        loadChildren: () => import('./place/place.module').then(m => m.TestPlaceModule),
      },
      {
        path: 'device',
        loadChildren: () => import('./device/device.module').then(m => m.TestDeviceModule),
      },
      {
        path: 'temperature',
        loadChildren: () => import('./temperature/temperature.module').then(m => m.TestTemperatureModule),
      },
      {
        path: 'values',
        loadChildren: () => import('./values/values.module').then(m => m.TestValuesModule),
      },
      {
        path: 'web-user',
        loadChildren: () => import('./web-user/web-user.module').then(m => m.TestWebUserModule),
      },
      {
        path: 'role',
        loadChildren: () => import('./role/role.module').then(m => m.TestRoleModule),
      },
      {
        path: 'push-notification-token',
        loadChildren: () => import('./push-notification-token/push-notification-token.module').then(m => m.TestPushNotificationTokenModule),
      },
      {
        path: 'application',
        loadChildren: () => import('./application/application.module').then(m => m.TestApplicationModule),
      },
      {
        path: 'device-profile',
        loadChildren: () => import('./device-profile/device-profile.module').then(m => m.TestDeviceProfileModule),
      },
      {
        path: 'device-configuration',
        loadChildren: () => import('./device-configuration/device-configuration.module').then(m => m.TestDeviceConfigurationModule),
      },
      {
        path: 'sms-notification',
        loadChildren: () => import('./sms-notification/sms-notification.module').then(m => m.TestSMSNotificationModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class TestEntityModule {}
