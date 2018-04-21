package springboot.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.login.dao.LoginDao;
import springboot.login.domain.User;
import java.util.List;

/**
 * Created by huangds on 2017/10/28.
 */
@Service
public class LoginService {

    @Autowired
    private LoginDao loginDao;

  public boolean verifyLogin(User user){

     List<User> userList = loginDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
      return userList.size()>0;
  }

}
