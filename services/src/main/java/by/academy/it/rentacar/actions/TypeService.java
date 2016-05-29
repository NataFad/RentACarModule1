package by.academy.it.rentacar.actions;


import by.academy.it.rentacar.dao.TypeDAO;
import by.academy.it.rentacar.entity.Type;

import java.util.ArrayList;

/**
 * Class TypeService induces TypeDAO
 *
 * @author Fadeeva Natallia
 * @version 1.2
 * @since 2016-05
 *
 */
public class TypeService implements ITypeService{

    private volatile static TypeService instance;
    private TypeDAO typeDAO;

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
        //try {
            typeList = (ArrayList<Type>) typeDAO.getAll();
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }
        return typeList;
    }

    /**
     * Method getTypeById() gets type by id
     *
     * @param typeId
     * @return type
     */
    public Type getTypeById(int typeId){
        Type type = null;
      //  try {
            type = typeDAO.getByKey("id", typeId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return type;
    }
}
