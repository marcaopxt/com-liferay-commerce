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

package com.liferay.commerce.machine.learning.forecast.alert.model.impl;

import com.liferay.commerce.machine.learning.forecast.alert.model.CommerceMLForecastAlertEntry;
import com.liferay.commerce.machine.learning.forecast.alert.model.CommerceMLForecastAlertEntryModel;
import com.liferay.commerce.machine.learning.forecast.alert.model.CommerceMLForecastAlertEntrySoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the CommerceMLForecastAlertEntry service. Represents a row in the &quot;CommerceMLForecastAlertEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>CommerceMLForecastAlertEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceMLForecastAlertEntryImpl}.
 * </p>
 *
 * @author Riccardo Ferrari
 * @see CommerceMLForecastAlertEntryImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceMLForecastAlertEntryModelImpl
	extends BaseModelImpl<CommerceMLForecastAlertEntry>
	implements CommerceMLForecastAlertEntryModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce ml forecast alert entry model instance should use the <code>CommerceMLForecastAlertEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommerceMLForecastAlertEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR},
		{"commerceMLForecastAlertEntryId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"commerceAccountId", Types.BIGINT},
		{"actual", Types.DOUBLE}, {"forecast", Types.DOUBLE},
		{"timestamp", Types.TIMESTAMP}, {"relativeChange", Types.DOUBLE},
		{"status", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commerceMLForecastAlertEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commerceAccountId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("actual", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("forecast", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("timestamp", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("relativeChange", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommerceMLForecastAlertEntry (uuid_ VARCHAR(75) null,commerceMLForecastAlertEntryId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commerceAccountId LONG,actual DOUBLE,forecast DOUBLE,timestamp DATE null,relativeChange DOUBLE,status INTEGER)";

	public static final String TABLE_SQL_DROP =
		"drop table CommerceMLForecastAlertEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceMLForecastAlertEntry.timestamp ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommerceMLForecastAlertEntry.timestamp ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.machine.learning.forecast.alert.service.util.
			ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.commerce.machine.learning.forecast.alert.model.CommerceMLForecastAlertEntry"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.machine.learning.forecast.alert.service.util.
			ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.commerce.machine.learning.forecast.alert.model.CommerceMLForecastAlertEntry"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.machine.learning.forecast.alert.service.util.
			ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.commerce.machine.learning.forecast.alert.model.CommerceMLForecastAlertEntry"),
		true);

	public static final long COMMERCEACCOUNTID_COLUMN_BITMASK = 1L;

	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	public static final long RELATIVECHANGE_COLUMN_BITMASK = 4L;

	public static final long STATUS_COLUMN_BITMASK = 8L;

	public static final long TIMESTAMP_COLUMN_BITMASK = 16L;

	public static final long UUID_COLUMN_BITMASK = 32L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CommerceMLForecastAlertEntry toModel(
		CommerceMLForecastAlertEntrySoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		CommerceMLForecastAlertEntry model =
			new CommerceMLForecastAlertEntryImpl();

		model.setUuid(soapModel.getUuid());
		model.setCommerceMLForecastAlertEntryId(
			soapModel.getCommerceMLForecastAlertEntryId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCommerceAccountId(soapModel.getCommerceAccountId());
		model.setActual(soapModel.getActual());
		model.setForecast(soapModel.getForecast());
		model.setTimestamp(soapModel.getTimestamp());
		model.setRelativeChange(soapModel.getRelativeChange());
		model.setStatus(soapModel.getStatus());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CommerceMLForecastAlertEntry> toModels(
		CommerceMLForecastAlertEntrySoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CommerceMLForecastAlertEntry> models =
			new ArrayList<CommerceMLForecastAlertEntry>(soapModels.length);

		for (CommerceMLForecastAlertEntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.machine.learning.forecast.alert.service.util.
			ServiceProps.get(
				"lock.expiration.time.com.liferay.commerce.machine.learning.forecast.alert.model.CommerceMLForecastAlertEntry"));

	public CommerceMLForecastAlertEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceMLForecastAlertEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceMLForecastAlertEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceMLForecastAlertEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceMLForecastAlertEntry.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceMLForecastAlertEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceMLForecastAlertEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceMLForecastAlertEntry, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceMLForecastAlertEntry, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(CommerceMLForecastAlertEntry)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceMLForecastAlertEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceMLForecastAlertEntry, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceMLForecastAlertEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceMLForecastAlertEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceMLForecastAlertEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CommerceMLForecastAlertEntry>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommerceMLForecastAlertEntry.class.getClassLoader(),
			CommerceMLForecastAlertEntry.class, ModelWrapper.class);

		try {
			Constructor<CommerceMLForecastAlertEntry> constructor =
				(Constructor<CommerceMLForecastAlertEntry>)
					proxyClass.getConstructor(InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map
		<String, Function<CommerceMLForecastAlertEntry, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<CommerceMLForecastAlertEntry, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceMLForecastAlertEntry, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<CommerceMLForecastAlertEntry, Object>>();
		Map<String, BiConsumer<CommerceMLForecastAlertEntry, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<CommerceMLForecastAlertEntry, ?>>();

		attributeGetterFunctions.put(
			"uuid",
			new Function<CommerceMLForecastAlertEntry, Object>() {

				@Override
				public Object apply(
					CommerceMLForecastAlertEntry commerceMLForecastAlertEntry) {

					return commerceMLForecastAlertEntry.getUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"uuid",
			new BiConsumer<CommerceMLForecastAlertEntry, Object>() {

				@Override
				public void accept(
					CommerceMLForecastAlertEntry commerceMLForecastAlertEntry,
					Object uuid) {

					commerceMLForecastAlertEntry.setUuid((String)uuid);
				}

			});
		attributeGetterFunctions.put(
			"commerceMLForecastAlertEntryId",
			new Function<CommerceMLForecastAlertEntry, Object>() {

				@Override
				public Object apply(
					CommerceMLForecastAlertEntry commerceMLForecastAlertEntry) {

					return commerceMLForecastAlertEntry.
						getCommerceMLForecastAlertEntryId();
				}

			});
		attributeSetterBiConsumers.put(
			"commerceMLForecastAlertEntryId",
			new BiConsumer<CommerceMLForecastAlertEntry, Object>() {

				@Override
				public void accept(
					CommerceMLForecastAlertEntry commerceMLForecastAlertEntry,
					Object commerceMLForecastAlertEntryId) {

					commerceMLForecastAlertEntry.
						setCommerceMLForecastAlertEntryId(
							(Long)commerceMLForecastAlertEntryId);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<CommerceMLForecastAlertEntry, Object>() {

				@Override
				public Object apply(
					CommerceMLForecastAlertEntry commerceMLForecastAlertEntry) {

					return commerceMLForecastAlertEntry.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<CommerceMLForecastAlertEntry, Object>() {

				@Override
				public void accept(
					CommerceMLForecastAlertEntry commerceMLForecastAlertEntry,
					Object companyId) {

					commerceMLForecastAlertEntry.setCompanyId((Long)companyId);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<CommerceMLForecastAlertEntry, Object>() {

				@Override
				public Object apply(
					CommerceMLForecastAlertEntry commerceMLForecastAlertEntry) {

					return commerceMLForecastAlertEntry.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<CommerceMLForecastAlertEntry, Object>() {

				@Override
				public void accept(
					CommerceMLForecastAlertEntry commerceMLForecastAlertEntry,
					Object userId) {

					commerceMLForecastAlertEntry.setUserId((Long)userId);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<CommerceMLForecastAlertEntry, Object>() {

				@Override
				public Object apply(
					CommerceMLForecastAlertEntry commerceMLForecastAlertEntry) {

					return commerceMLForecastAlertEntry.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<CommerceMLForecastAlertEntry, Object>() {

				@Override
				public void accept(
					CommerceMLForecastAlertEntry commerceMLForecastAlertEntry,
					Object userName) {

					commerceMLForecastAlertEntry.setUserName((String)userName);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<CommerceMLForecastAlertEntry, Object>() {

				@Override
				public Object apply(
					CommerceMLForecastAlertEntry commerceMLForecastAlertEntry) {

					return commerceMLForecastAlertEntry.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<CommerceMLForecastAlertEntry, Object>() {

				@Override
				public void accept(
					CommerceMLForecastAlertEntry commerceMLForecastAlertEntry,
					Object createDate) {

					commerceMLForecastAlertEntry.setCreateDate(
						(Date)createDate);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<CommerceMLForecastAlertEntry, Object>() {

				@Override
				public Object apply(
					CommerceMLForecastAlertEntry commerceMLForecastAlertEntry) {

					return commerceMLForecastAlertEntry.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<CommerceMLForecastAlertEntry, Object>() {

				@Override
				public void accept(
					CommerceMLForecastAlertEntry commerceMLForecastAlertEntry,
					Object modifiedDate) {

					commerceMLForecastAlertEntry.setModifiedDate(
						(Date)modifiedDate);
				}

			});
		attributeGetterFunctions.put(
			"commerceAccountId",
			new Function<CommerceMLForecastAlertEntry, Object>() {

				@Override
				public Object apply(
					CommerceMLForecastAlertEntry commerceMLForecastAlertEntry) {

					return commerceMLForecastAlertEntry.getCommerceAccountId();
				}

			});
		attributeSetterBiConsumers.put(
			"commerceAccountId",
			new BiConsumer<CommerceMLForecastAlertEntry, Object>() {

				@Override
				public void accept(
					CommerceMLForecastAlertEntry commerceMLForecastAlertEntry,
					Object commerceAccountId) {

					commerceMLForecastAlertEntry.setCommerceAccountId(
						(Long)commerceAccountId);
				}

			});
		attributeGetterFunctions.put(
			"actual",
			new Function<CommerceMLForecastAlertEntry, Object>() {

				@Override
				public Object apply(
					CommerceMLForecastAlertEntry commerceMLForecastAlertEntry) {

					return commerceMLForecastAlertEntry.getActual();
				}

			});
		attributeSetterBiConsumers.put(
			"actual",
			new BiConsumer<CommerceMLForecastAlertEntry, Object>() {

				@Override
				public void accept(
					CommerceMLForecastAlertEntry commerceMLForecastAlertEntry,
					Object actual) {

					commerceMLForecastAlertEntry.setActual((Double)actual);
				}

			});
		attributeGetterFunctions.put(
			"forecast",
			new Function<CommerceMLForecastAlertEntry, Object>() {

				@Override
				public Object apply(
					CommerceMLForecastAlertEntry commerceMLForecastAlertEntry) {

					return commerceMLForecastAlertEntry.getForecast();
				}

			});
		attributeSetterBiConsumers.put(
			"forecast",
			new BiConsumer<CommerceMLForecastAlertEntry, Object>() {

				@Override
				public void accept(
					CommerceMLForecastAlertEntry commerceMLForecastAlertEntry,
					Object forecast) {

					commerceMLForecastAlertEntry.setForecast((Double)forecast);
				}

			});
		attributeGetterFunctions.put(
			"timestamp",
			new Function<CommerceMLForecastAlertEntry, Object>() {

				@Override
				public Object apply(
					CommerceMLForecastAlertEntry commerceMLForecastAlertEntry) {

					return commerceMLForecastAlertEntry.getTimestamp();
				}

			});
		attributeSetterBiConsumers.put(
			"timestamp",
			new BiConsumer<CommerceMLForecastAlertEntry, Object>() {

				@Override
				public void accept(
					CommerceMLForecastAlertEntry commerceMLForecastAlertEntry,
					Object timestamp) {

					commerceMLForecastAlertEntry.setTimestamp((Date)timestamp);
				}

			});
		attributeGetterFunctions.put(
			"relativeChange",
			new Function<CommerceMLForecastAlertEntry, Object>() {

				@Override
				public Object apply(
					CommerceMLForecastAlertEntry commerceMLForecastAlertEntry) {

					return commerceMLForecastAlertEntry.getRelativeChange();
				}

			});
		attributeSetterBiConsumers.put(
			"relativeChange",
			new BiConsumer<CommerceMLForecastAlertEntry, Object>() {

				@Override
				public void accept(
					CommerceMLForecastAlertEntry commerceMLForecastAlertEntry,
					Object relativeChange) {

					commerceMLForecastAlertEntry.setRelativeChange(
						(Double)relativeChange);
				}

			});
		attributeGetterFunctions.put(
			"status",
			new Function<CommerceMLForecastAlertEntry, Object>() {

				@Override
				public Object apply(
					CommerceMLForecastAlertEntry commerceMLForecastAlertEntry) {

					return commerceMLForecastAlertEntry.getStatus();
				}

			});
		attributeSetterBiConsumers.put(
			"status",
			new BiConsumer<CommerceMLForecastAlertEntry, Object>() {

				@Override
				public void accept(
					CommerceMLForecastAlertEntry commerceMLForecastAlertEntry,
					Object status) {

					commerceMLForecastAlertEntry.setStatus((Integer)status);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getCommerceMLForecastAlertEntryId() {
		return _commerceMLForecastAlertEntryId;
	}

	@Override
	public void setCommerceMLForecastAlertEntryId(
		long commerceMLForecastAlertEntryId) {

		_commerceMLForecastAlertEntryId = commerceMLForecastAlertEntryId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getCommerceAccountId() {
		return _commerceAccountId;
	}

	@Override
	public void setCommerceAccountId(long commerceAccountId) {
		_columnBitmask |= COMMERCEACCOUNTID_COLUMN_BITMASK;

		if (!_setOriginalCommerceAccountId) {
			_setOriginalCommerceAccountId = true;

			_originalCommerceAccountId = _commerceAccountId;
		}

		_commerceAccountId = commerceAccountId;
	}

	public long getOriginalCommerceAccountId() {
		return _originalCommerceAccountId;
	}

	@JSON
	@Override
	public double getActual() {
		return _actual;
	}

	@Override
	public void setActual(double actual) {
		_actual = actual;
	}

	@JSON
	@Override
	public double getForecast() {
		return _forecast;
	}

	@Override
	public void setForecast(double forecast) {
		_forecast = forecast;
	}

	@JSON
	@Override
	public Date getTimestamp() {
		return _timestamp;
	}

	@Override
	public void setTimestamp(Date timestamp) {
		_columnBitmask = -1L;

		if (_originalTimestamp == null) {
			_originalTimestamp = _timestamp;
		}

		_timestamp = timestamp;
	}

	public Date getOriginalTimestamp() {
		return _originalTimestamp;
	}

	@JSON
	@Override
	public double getRelativeChange() {
		return _relativeChange;
	}

	@Override
	public void setRelativeChange(double relativeChange) {
		_columnBitmask |= RELATIVECHANGE_COLUMN_BITMASK;

		if (!_setOriginalRelativeChange) {
			_setOriginalRelativeChange = true;

			_originalRelativeChange = _relativeChange;
		}

		_relativeChange = relativeChange;
	}

	public double getOriginalRelativeChange() {
		return _originalRelativeChange;
	}

	@JSON
	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_columnBitmask |= STATUS_COLUMN_BITMASK;

		if (!_setOriginalStatus) {
			_setOriginalStatus = true;

			_originalStatus = _status;
		}

		_status = status;
	}

	public int getOriginalStatus() {
		return _originalStatus;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(
				CommerceMLForecastAlertEntry.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), CommerceMLForecastAlertEntry.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceMLForecastAlertEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceMLForecastAlertEntry>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CommerceMLForecastAlertEntryImpl commerceMLForecastAlertEntryImpl =
			new CommerceMLForecastAlertEntryImpl();

		commerceMLForecastAlertEntryImpl.setUuid(getUuid());
		commerceMLForecastAlertEntryImpl.setCommerceMLForecastAlertEntryId(
			getCommerceMLForecastAlertEntryId());
		commerceMLForecastAlertEntryImpl.setCompanyId(getCompanyId());
		commerceMLForecastAlertEntryImpl.setUserId(getUserId());
		commerceMLForecastAlertEntryImpl.setUserName(getUserName());
		commerceMLForecastAlertEntryImpl.setCreateDate(getCreateDate());
		commerceMLForecastAlertEntryImpl.setModifiedDate(getModifiedDate());
		commerceMLForecastAlertEntryImpl.setCommerceAccountId(
			getCommerceAccountId());
		commerceMLForecastAlertEntryImpl.setActual(getActual());
		commerceMLForecastAlertEntryImpl.setForecast(getForecast());
		commerceMLForecastAlertEntryImpl.setTimestamp(getTimestamp());
		commerceMLForecastAlertEntryImpl.setRelativeChange(getRelativeChange());
		commerceMLForecastAlertEntryImpl.setStatus(getStatus());

		commerceMLForecastAlertEntryImpl.resetOriginalValues();

		return commerceMLForecastAlertEntryImpl;
	}

	@Override
	public int compareTo(
		CommerceMLForecastAlertEntry commerceMLForecastAlertEntry) {

		int value = 0;

		value = DateUtil.compareTo(
			getTimestamp(), commerceMLForecastAlertEntry.getTimestamp());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceMLForecastAlertEntry)) {
			return false;
		}

		CommerceMLForecastAlertEntry commerceMLForecastAlertEntry =
			(CommerceMLForecastAlertEntry)obj;

		long primaryKey = commerceMLForecastAlertEntry.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		CommerceMLForecastAlertEntryModelImpl
			commerceMLForecastAlertEntryModelImpl = this;

		commerceMLForecastAlertEntryModelImpl._originalUuid =
			commerceMLForecastAlertEntryModelImpl._uuid;

		commerceMLForecastAlertEntryModelImpl._originalCompanyId =
			commerceMLForecastAlertEntryModelImpl._companyId;

		commerceMLForecastAlertEntryModelImpl._setOriginalCompanyId = false;

		commerceMLForecastAlertEntryModelImpl._setModifiedDate = false;

		commerceMLForecastAlertEntryModelImpl._originalCommerceAccountId =
			commerceMLForecastAlertEntryModelImpl._commerceAccountId;

		commerceMLForecastAlertEntryModelImpl._setOriginalCommerceAccountId =
			false;

		commerceMLForecastAlertEntryModelImpl._originalTimestamp =
			commerceMLForecastAlertEntryModelImpl._timestamp;

		commerceMLForecastAlertEntryModelImpl._originalRelativeChange =
			commerceMLForecastAlertEntryModelImpl._relativeChange;

		commerceMLForecastAlertEntryModelImpl._setOriginalRelativeChange =
			false;

		commerceMLForecastAlertEntryModelImpl._originalStatus =
			commerceMLForecastAlertEntryModelImpl._status;

		commerceMLForecastAlertEntryModelImpl._setOriginalStatus = false;

		commerceMLForecastAlertEntryModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceMLForecastAlertEntry> toCacheModel() {
		CommerceMLForecastAlertEntryCacheModel
			commerceMLForecastAlertEntryCacheModel =
				new CommerceMLForecastAlertEntryCacheModel();

		commerceMLForecastAlertEntryCacheModel.uuid = getUuid();

		String uuid = commerceMLForecastAlertEntryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			commerceMLForecastAlertEntryCacheModel.uuid = null;
		}

		commerceMLForecastAlertEntryCacheModel.commerceMLForecastAlertEntryId =
			getCommerceMLForecastAlertEntryId();

		commerceMLForecastAlertEntryCacheModel.companyId = getCompanyId();

		commerceMLForecastAlertEntryCacheModel.userId = getUserId();

		commerceMLForecastAlertEntryCacheModel.userName = getUserName();

		String userName = commerceMLForecastAlertEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceMLForecastAlertEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceMLForecastAlertEntryCacheModel.createDate =
				createDate.getTime();
		}
		else {
			commerceMLForecastAlertEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceMLForecastAlertEntryCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commerceMLForecastAlertEntryCacheModel.modifiedDate =
				Long.MIN_VALUE;
		}

		commerceMLForecastAlertEntryCacheModel.commerceAccountId =
			getCommerceAccountId();

		commerceMLForecastAlertEntryCacheModel.actual = getActual();

		commerceMLForecastAlertEntryCacheModel.forecast = getForecast();

		Date timestamp = getTimestamp();

		if (timestamp != null) {
			commerceMLForecastAlertEntryCacheModel.timestamp =
				timestamp.getTime();
		}
		else {
			commerceMLForecastAlertEntryCacheModel.timestamp = Long.MIN_VALUE;
		}

		commerceMLForecastAlertEntryCacheModel.relativeChange =
			getRelativeChange();

		commerceMLForecastAlertEntryCacheModel.status = getStatus();

		return commerceMLForecastAlertEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceMLForecastAlertEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceMLForecastAlertEntry, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceMLForecastAlertEntry, Object>
				attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply(
					(CommerceMLForecastAlertEntry)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<CommerceMLForecastAlertEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CommerceMLForecastAlertEntry, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceMLForecastAlertEntry, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply(
					(CommerceMLForecastAlertEntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, CommerceMLForecastAlertEntry>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private String _uuid;
	private String _originalUuid;
	private long _commerceMLForecastAlertEntryId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commerceAccountId;
	private long _originalCommerceAccountId;
	private boolean _setOriginalCommerceAccountId;
	private double _actual;
	private double _forecast;
	private Date _timestamp;
	private Date _originalTimestamp;
	private double _relativeChange;
	private double _originalRelativeChange;
	private boolean _setOriginalRelativeChange;
	private int _status;
	private int _originalStatus;
	private boolean _setOriginalStatus;
	private long _columnBitmask;
	private CommerceMLForecastAlertEntry _escapedModel;

}