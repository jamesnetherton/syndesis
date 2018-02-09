import { NgModule, forwardRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SyndesisVendorModule } from '@syndesis/ui/vendor.module';
import { SyndesisCommonModule } from '@syndesis/ui/common';
import { IntegrationSupportModule } from '../integration-support.module';
import { IntegrationStatusComponent } from './status.component';
import { IntegrationActionMenuComponent } from './action-menu.component';
import { IntegrationListComponent } from './list.component';

const syndesisCommonModuleFwd = forwardRef(() => SyndesisCommonModule);
const integrationSupportModuleFwd = forwardRef(() => IntegrationSupportModule);

@NgModule({
  imports: [
    CommonModule,
    SyndesisVendorModule,
    syndesisCommonModuleFwd,
    integrationSupportModuleFwd
  ],
  declarations: [IntegrationActionMenuComponent, IntegrationStatusComponent, IntegrationListComponent],
  exports: [IntegrationActionMenuComponent, IntegrationListComponent, IntegrationStatusComponent]
})
export class IntegrationListModule {}
