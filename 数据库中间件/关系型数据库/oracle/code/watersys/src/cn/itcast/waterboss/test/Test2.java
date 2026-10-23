package cn.itcast.waterboss.test;

import cn.itcast.waterboss.dao.OwnersDao;
import cn.itcast.waterboss.entity.Owners;

import java.util.Date;

public class Test2 {

    public static void main(String[] args) {
        Owners owners = new Owners();
        owners.setId(1L);
        owners.setName("王强");
        owners.setAddress(1L);
        owners.setHouseNumber("5555");
        owners.setWaterMeter("3456");
        owners.setAddDate(new Date());
        owners.setOwnerTypeId(1L);

        OwnersDao.update(owners);
    }

}
