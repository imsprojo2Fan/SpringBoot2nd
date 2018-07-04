package com.hhen.mapper;

import com.hhen.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: imsprojo2Fan
 * @Description:
 * @Date: Created in 9:31 2018/7/2
 * @Modified By:
 */
// @Mapper 这里可以使用@Mapper注解，但是每个mapper都加注解比较麻烦，所以统一配置@MapperScan在扫描路径在application类中
public interface UserMapper {
	@Select("SELECT * FROM menu WHERE id = #{id}")
	User getUserById(Integer id);

	@Select("SELECT * FROM menu")
	public List<User> getUserList();

	@Insert("insert into menu(username, age, ctm) values(#{username}, #{age}, now())")
	public int add(User user);

	@Update("UPDATE menu SET username = #{menu.username} , age = #{menu.age} WHERE id = #{id}")
	public int update(@Param("id") Integer id, @Param("menu") User user);

	@Delete("DELETE from menu where id = #{id} ")
	public int delete(Integer id);
}
