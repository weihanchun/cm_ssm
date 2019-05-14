package com.cm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.mapper.PropertyValueMapper;
import com.cm.pojo.Product;
import com.cm.pojo.Property;
import com.cm.pojo.PropertyValue;
import com.cm.service.PropertyService;
import com.cm.service.PropertyValueService;
@Service
public class PropertyValueServiceImpl implements PropertyValueService {
	@Autowired
	PropertyValueMapper propertyValueMapper;
	@Autowired
	PropertyService propertyService;

	/*init方法
	 *3.1 这个方法的作用是初始化PropertyValue。 
	 *为什么要初始化呢？ 因为对于PropertyValue的管理，没有增加，只有修改。 所以需要通过初始化来进行自动地增加，以便于后面的修改。
	 *3.2 首先根据产品获取分类，然后获取这个分类下的所有属性集合
	 *3.3 然后用属性id和产品id去查询，看看这个属性和这个产品，是否已经存在属性值了。
	 *3.4 如果不存在，那么就创建一个属性值，并设置其属性和产品，接着插入到数据库中。
	 *这样就完成了属性值的初始化。
	 * */
	@Override
	public void init(Product p) {
		List<Property> pp=propertyService.list(p.getCid());
		for(Property pt:pp) {
			PropertyValue pv=get(pt.getId(),p.getId());
			if(null==pv) {
				pv=new PropertyValue();
				pv.setPid(p.getId());
				pv.setPtid(pt.getId());
				propertyValueMapper.add(pv);
			}
		}
	}

	/*更新*/
	@Override
	public void update(PropertyValue pv) {
		propertyValueMapper.update(pv);
	}

	/*根据属性id和产品id获取PropertyValue对象*/
	@Override
	public PropertyValue get(int ptid, int pid) {
		return propertyValueMapper.get(pid, ptid);
	}
 
	/*根据产品id获取所有的属性值*/
	@Override
	public List<PropertyValue> list(int pid) {
		List<PropertyValue> result=propertyValueMapper.list(pid);
		for(PropertyValue pv:result) {
			Property property=propertyService.get(pv.getPtid());
			pv.setProperty(property);
		}
		return result;
	}

	/*@Override
	public void fill(Product p) {
		// TODO Auto-generated method stub
		
	}
*/
}
