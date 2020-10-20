package com.backpacker.yflLibrary.view.dialog.addresspicker;


import com.backpacker.yflLibrary.view.dialog.addresspicker.entity.City;
import com.backpacker.yflLibrary.view.dialog.addresspicker.entity.County;
import com.backpacker.yflLibrary.view.dialog.addresspicker.entity.Province;

/**
 * @author matt
 * blog: addapp.cn
 */

public interface OnLinkageListener {
    /**
     * 选择地址
     *
     * @param province the province
     * @param city    the city
     * @param county   the county ，if {@code hideCounty} is true，this is null
     */
    void onAddressPicked(Province province, City city, County county);
}
