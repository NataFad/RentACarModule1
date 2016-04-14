package by.academy.it.rentacar.actions;


import by.academy.it.rentacar.beans.Type;
import by.academy.it.rentacar.dao.TypeDAO;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class TypeService induces TypeDAO
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 *
 */
public class TypeService {

    private volatile static TypeService instance;

    private TypeService(){}

    public static TypeService getInstance() {
        if (instance == null) {
            synchronized (TypeService.class) {
                if (instance == null) {
                    instance = new TypeService();
                }
            }
        }
        return instance;
    }

    /**
     * Method getListType() gets list of types
     *
     * @return ArrayList<Type>
     */
    public ArrayList<Type> getListType(){
        // List of types
        ArrayList<Type> typeList = new ArrayList<Type>();
        try {
            typeList = TypeDAO.getInstance().getAll();
        } catch (SQLException e) {
            Logger.getLogger(TypeDAO.class).error(e.getMessage());
        }
        return typeList;
    }

    /**
     * Method getById() calls the method in TypesDAO
     *
     * @param id
     * @return Type
     */
    public Type getById(int id){
        Type type = null;
        try {
            type = TypeDAO.getInstance().getById(id);
        } catch (SQLException e) {
            Logger.getLogger(TypeDAO.class).error(e.getMessage());
        }
        return type;
    }

}
