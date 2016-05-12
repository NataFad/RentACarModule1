package by.academy.it.rentacar.actions;


import by.academy.it.rentacar.entity.Type;
import by.academy.it.rentacar.dao.TypeDAO;
import by.academy.it.rentacar.exceptions.DAOException;

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
            typeList = (ArrayList<Type>) TypeDAO.getInstance().getAll();
        } catch (DAOException e) {
            e.printStackTrace();
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
            type = TypeDAO.getInstance().get(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return type;
    }

}
