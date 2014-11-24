package com.sarality.app.datastore.sms;

import android.app.Application;

import com.sarality.app.ModuleInitializer;
import com.sarality.app.common.collect.Lists;
import com.sarality.app.datastore.DataStore;
import com.sarality.app.datastore.db.Table;

import java.util.List;

/**
 * Initializer for SMS DataStore module.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class SmsModuleInitializer implements ModuleInitializer {

  @Override
  public List<Table<?>> getTables(Application application) {
    return null;
  }

  @Override
  public List<DataStore<?>> getDataStores(Application application) {
    List<DataStore<?>> dataStoreList = Lists.of();
    dataStoreList.add(new SmsDataStore(application.getApplicationContext()));
    return dataStoreList;
  }

  @Override
  public void initEnumDatas(Application application) {
    // No Enum Datas to initialize.
  }
}
