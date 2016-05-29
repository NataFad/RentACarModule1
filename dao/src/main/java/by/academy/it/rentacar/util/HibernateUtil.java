/*
 * Copyright (c) 2012 by VeriFone, Inc.
 * All Rights Reserved.
 *
 * THIS FILE CONTAINS PROPRIETARY AND CONFIDENTIAL INFORMATION
 * AND REMAINS THE UNPUBLISHED PROPERTY OF VERIFONE, INC.
 *
 * Use, disclosure, or reproduction is prohibited
 * without prior written approval from VeriFone, Inc.
 */
package by.academy.it.rentacar.util;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


/**
 * Class HibernateUtil
 * is
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 */
public class HibernateUtil{
    private static HibernateUtil util = null;
    private static Logger log = Logger.getLogger(HibernateUtil.class);
    private SessionFactory sessionFactory = null;
    private final ThreadLocal sessions = new ThreadLocal();

    private HibernateUtil() {
        try {
            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                    applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (Throwable ex) {
            log.error("Initial SessionFactory creation failed." + ex);
            System.exit(0);
        }
    }

    public Session getSession() {
        Session session = (Session) sessions.get();
        if (session == null) {
            session = sessionFactory.openSession();
            sessions.set(session);
        }
        return session;
    }

    public static HibernateUtil getInstance() {
        if (util == null) {
            synchronized (HibernateUtil.class) {
                if (util == null) {
                    util = new HibernateUtil();
                }
            }
        }
        return util;
    }

    public void closeSession() {
        Session session = (Session) sessions.get();
        sessions.set(null);
        if (session != null) {
            if (session.isOpen()) {
                session.close();
            }
        }
    }
}
