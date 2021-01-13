package com.demo.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 解决实体管理器工厂创建浪费资源和耗时的问题
 *      通过静态代码块配置
 */
public class JpaUtils {

        private static EntityManagerFactory managerFactory ;

        static{
            managerFactory = Persistence.createEntityManagerFactory("myJPA");
        }

        /**
         * 获取EntityManager对象
         * @return
         */
        public static EntityManager getEntityManage(){
            return managerFactory.createEntityManager();
        }


}
