package com.neu.lend.biz.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neu.book.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.neu.lend.biz.LendBiz;
import com.neu.lend.mapper.LendMapper;
import com.neu.lend.entity.Lend;
/**
 * @author 陈锦房
 */
@Service("lendBiz")
public class LendBizImpl implements LendBiz{

	@Autowired
	private LendMapper lendMapper;
	

    @Override
    public List<Lend> findLendByUsername(int index, int size,String username) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("index", index);
        map.put("size", size);
        map.put("username", username);
        List<Lend> list = lendMapper.findLendByUsername(map);
        return list;
    }

    @Override
    public int count() {
        int count =lendMapper.count();
        return count;
    }

    @Override
    public boolean deleteLend(String id) {
        return lendMapper.deleteLend(id);
    }


	@Override
	public boolean updateLend(Lend lend) {
		// TODO Auto-generated method stub
		return  lendMapper.updateLend(lend);
	}

	@Override
	public  List<HashMap<String, Object>> findById(String id) {
		// TODO Auto-generated method stub
		return lendMapper.findById(id);
	}

	@Override
	public List<Lend> findLend(int index, int size) {
		 Map<String, Object> map = new HashMap<String, Object>();
	        map.put("index", index);
	        map.put("size", size);      
	        List<Lend> list1 = lendMapper.findLend(map);
	        return list1;
	}

	@Override
	public boolean reback(Lend lend) {
		// TODO Auto-generated method stub
		return lendMapper.reback(lend);
	}
	@Override
	public List<Lend> findLendMo(int index, int size, String username) {
		Map<String,Object> map=new HashMap<String,Object>();
		//将参数传入map
		map.put("index",index);
		map.put("size",size);
		map.put("username",username);
		List<Lend> list=lendMapper.findLendMo(map);

		return list;
	}

	@Override
	public int countMo(String username) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("username",username);

		return  lendMapper.countMo(map);
	}
}
