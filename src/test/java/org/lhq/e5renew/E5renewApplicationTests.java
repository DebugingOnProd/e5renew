package org.lhq.e5renew;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.setting.Setting;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.lhq.e5renew.dao.AzureParamDao;
import org.lhq.e5renew.dao.UserDao;
import org.lhq.e5renew.entity.AzureParam;
import org.lhq.e5renew.entity.AzureParamRecord;
import org.lhq.e5renew.entity.OutlookConfig;
import org.lhq.e5renew.entity.User;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
class E5renewApplicationTests {

    @Resource
    AzureParamDao azureParamDao;
    @Resource
    UserDao userDao;

    @Test
    void contextLoads() {
        List<AzureParam> azureParams = azureParamDao.selectList(null);
        azureParams.forEach(azureParam -> {
            System.out.println(azureParam);
        });


    }
    @Test
    void contextLoads1() {
        Setting setting = new Setting("RSA.setting");
        RSA rsa = new RSA(setting.getStr("PrivateKey"),setting.getStr("PublicKey"));
        byte[] encrypt =rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
        byte[] decrypt = rsa.decrypt(Base64.decode(Base64.encode(encrypt)), KeyType.PrivateKey);
        System.out.println(StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8));
    }
    @Test
    void contextLoads2() {
        int i = 0;
        while (5>i){
            User user = new User();
            user.setAge(20);
            user.setEmail("18376680552@139.com");
            user.setName("赵四");
            userDao.insert(user);
            i++;
        }

    }
    @Test
    void contextLoads3() {
        //根据QueryWrapper进行更新
        User user = new User();
        user.setAge(20);
        user.setEmail("939296345@qq.com");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "张三");
        userDao.update(user, queryWrapper);
    }
    @Test
    void contextLoads4() {
        //根据updateWrapper进行更新
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("age", 22).eq("name", "张三");
        userDao.update(null, updateWrapper);
    }

    @Test
    void contextLoads5() {
        //删除操作,根据id
//        userDao.deleteById(2);
        //根据map
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "赵四");
//        map.put("age", 20);
//        userDao.deleteByMap(map);
        //根据QueryWrapper
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("name", "赵四").ge("age", 20);
//        userDao.delete(queryWrapper);
        //通过id批量删除
        List<Long> arrayList = new ArrayList<>();
        arrayList.add(5L);
        arrayList.add(6L);
        arrayList.add(7L);
        arrayList.add(8L);
        arrayList.add(9L);
        userDao.deleteBatchIds(arrayList);
    }
    @Test
    void contextLoads6() {
        //通过id查询
//        User user = userDao.selectById(1);
//        System.out.println(user.toString());
        //根据id集合批量查询
//        List<Long> arrayList = new ArrayList<>();
//        arrayList.add(10L);
//        arrayList.add(11L);
//        arrayList.add(12L);
//        arrayList.add(13L);
//        arrayList.add(14L);
//        List<User> users = userDao.selectBatchIds(arrayList);
//        System.out.println(users);
        //selectOne
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("name", "张三");
//        User user = userDao.selectOne(wrapper);
//        System.out.println(user);
        //根据Wrapper条件，查询总记录数
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("name", "赵四");
//        Long aLong = userDao.selectCount(wrapper);
//        System.out.println(aLong);
//        gt = greater than
//        lt = little than
//        其他的类似
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.ge("age", 22);
//        List<User> users = userDao.selectList(wrapper);
//        System.out.println(users);
        //模糊查询
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.like("name", "四");
//        List<User> users = userDao.selectList(wrapper);
//        System.out.println(users);
        //排序
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.orderBy(true, true, "id", "name");
//        wrapper.orderByAsc("id");
//        wrapper.orderByDesc("id");
//        userDao.selectList(wrapper);
        //主动调用or表示紧接着下一个方法不是用and连接!(不调用or则默认为使用and连接)
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("name", "张三").or().eq("age", 25).or(i ->i.eq("name","赵四5"));
        wrapper.eq("name", "张三").or().eq("age", 25).and(i ->i.eq("email","939296345@qq.com"));
        List<User> users = userDao.selectList(wrapper);
    }
    @Test
    void contextLoads7(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        List<Long> arrayList = new ArrayList<>();
        arrayList.add(10L);
        arrayList.add(11L);
        arrayList.add(12L);
        arrayList.add(13L);
        arrayList.add(14L);
        wrapper.in("id", arrayList);
        Long aLong = userDao.selectCount(wrapper);
        System.out.println(aLong);


    }

}
