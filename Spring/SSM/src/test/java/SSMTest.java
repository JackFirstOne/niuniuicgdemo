import com.ssm.pojo.Item;
import com.ssm.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-dao.xml","classpath:applicationContext-service.xml"})
public class SSMTest {

     @Autowired
     private ItemService itemService;

    @Test
      public void test1(){
        Item itemById = itemService.findItemById(1);
        System.out.println(itemById);

    }
}
