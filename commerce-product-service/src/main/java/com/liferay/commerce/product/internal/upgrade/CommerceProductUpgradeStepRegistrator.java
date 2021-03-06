/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.product.internal.upgrade;

import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.commerce.product.internal.upgrade.v1_10_1.CommerceSiteTypeUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v1_11_0.CPAttachmentFileEntryGroupUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v1_11_1.CPDisplayLayoutUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v1_2_0.ProductSubscriptionUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v1_3_0.CPAttachmentFileEntryUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v1_3_0.CPDefinitionLinkUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v1_3_0.CPDefinitionOptionRelUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v1_3_0.CPDefinitionUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v1_3_0.CPFriendlyURLEntryUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v1_3_0.CPInstanceUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v1_3_0.CProductUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v1_4_0.CPDefinitionSpecificationOptionValueUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v1_5_0.CProductExternalReferenceCodeUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v1_6_0.CPDefinitionTrashEntriesUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v1_6_0.CommerceCatalogUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v1_6_0.CommerceChannelUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v1_7_0.CPDefinitionFiltersUpgradeProcess;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.settings.SettingsFactory;
import com.liferay.portal.kernel.upgrade.DummyUpgradeProcess;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ethan Bustad
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class CommerceProductUpgradeStepRegistrator
	implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		if (_log.isInfoEnabled()) {
			_log.info("COMMERCE PRODUCT UPGRADE STEP REGISTRATOR STARTED");
		}

		registry.register(
			_SCHEMA_VERSION_1_0_0, _SCHEMA_VERSION_1_1_0,
			new DummyUpgradeProcess());

		registry.register(
			_SCHEMA_VERSION_1_1_0, _SCHEMA_VERSION_1_2_0,
			new ProductSubscriptionUpgradeProcess());

		registry.register(
			_SCHEMA_VERSION_1_2_0, _SCHEMA_VERSION_1_3_0,
			new CPAttachmentFileEntryUpgradeProcess(),
			new CPDefinitionLinkUpgradeProcess(),
			new CPDefinitionOptionRelUpgradeProcess(),
			new CPDefinitionUpgradeProcess(), new CProductUpgradeProcess(),
			new CPFriendlyURLEntryUpgradeProcess(_classNameLocalService),
			new CPInstanceUpgradeProcess());

		registry.register(
			_SCHEMA_VERSION_1_3_0, _SCHEMA_VERSION_1_4_0,
			new CPDefinitionSpecificationOptionValueUpgradeProcess());

		registry.register(
			_SCHEMA_VERSION_1_4_0, _SCHEMA_VERSION_1_5_0,
			new CProductExternalReferenceCodeUpgradeProcess());

		registry.register(
			_SCHEMA_VERSION_1_5_0, _SCHEMA_VERSION_1_6_0,
			new CommerceChannelUpgradeProcess(),
			new CommerceCatalogUpgradeProcess(_groupLocalService),
			new CPDefinitionTrashEntriesUpgradeProcess(_classNameLocalService));

		registry.register(
			_SCHEMA_VERSION_1_6_0, _SCHEMA_VERSION_1_7_0,
			new CPDefinitionFiltersUpgradeProcess());

		registry.register(
			_SCHEMA_VERSION_1_7_0, _SCHEMA_VERSION_1_8_0,
			new com.liferay.commerce.product.internal.upgrade.v1_8_0.
				CPAttachmentFileEntryUpgradeProcess(_classNameLocalService));

		registry.register(
			_SCHEMA_VERSION_1_8_0, _SCHEMA_VERSION_1_9_0,
			new com.liferay.commerce.product.internal.upgrade.v1_9_0.
				CPDefinitionOptionRelUpgradeProcess());

		registry.register(
			_SCHEMA_VERSION_1_9_0, _SCHEMA_VERSION_1_10_0,
			new com.liferay.commerce.product.internal.upgrade.v1_10_0.
				CPAttachmentFileEntryUpgradeProcess(_jsonFactory),
			new com.liferay.commerce.product.internal.upgrade.v1_10_0.
				CPInstanceUpgradeProcess(_jsonFactory));

		registry.register(
			_SCHEMA_VERSION_1_10_0, _SCHEMA_VERSION_1_10_1,
			new CommerceSiteTypeUpgradeProcess(
				_classNameLocalService, _groupLocalService,
				_configurationProvider, _settingsFactory));

		registry.register(
			_SCHEMA_VERSION_1_10_1, _SCHEMA_VERSION_1_11_0,
			new CPAttachmentFileEntryGroupUpgradeProcess(
				_assetCategoryLocalService, _classNameLocalService));

		registry.register(
			_SCHEMA_VERSION_1_11_0, _SCHEMA_VERSION_1_11_1,
			new CPDisplayLayoutUpgradeProcess(_layoutLocalService));

		if (_log.isInfoEnabled()) {
			_log.info("COMMERCE PRODUCT UPGRADE STEP REGISTRATOR FINISHED");
		}
	}

	private static final String _SCHEMA_VERSION_1_0_0 = "1.0.0";

	private static final String _SCHEMA_VERSION_1_1_0 = "1.1.0";

	private static final String _SCHEMA_VERSION_1_2_0 = "1.2.0";

	private static final String _SCHEMA_VERSION_1_3_0 = "1.3.0";

	private static final String _SCHEMA_VERSION_1_4_0 = "1.4.0";

	private static final String _SCHEMA_VERSION_1_5_0 = "1.5.0";

	private static final String _SCHEMA_VERSION_1_6_0 = "1.6.0";

	private static final String _SCHEMA_VERSION_1_7_0 = "1.7.0";

	private static final String _SCHEMA_VERSION_1_8_0 = "1.8.0";

	private static final String _SCHEMA_VERSION_1_9_0 = "1.9.0";

	private static final String _SCHEMA_VERSION_1_10_0 = "1.10.0";

	private static final String _SCHEMA_VERSION_1_10_1 = "1.10.1";

	private static final String _SCHEMA_VERSION_1_11_0 = "1.11.0";

	private static final String _SCHEMA_VERSION_1_11_1 = "1.11.1";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceProductUpgradeStepRegistrator.class);

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private SettingsFactory _settingsFactory;

}