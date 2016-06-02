package by.academy.it.rentacar.actions.impl;


import by.academy.it.rentacar.actions.ITypeService;
import by.academy.it.rentacar.dao.ITypeDAO;
import by.academy.it.rentacar.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Class TypeService induces TypeDAO
 *
 * @author Fadeeva Natallia
 * @version 1.3
 * @since 2016-05
 */
@Service("typeService")
@Transactional(readOnly = true)
public class TypeService implements ITypeService {

    @Autowired
    private ITypeDAO typeDAO;

    /**
     * Method getListType() gets list of types
     *
     * @return ArrayList<Type>
     */
    public ArrayList<Type> getListType() {
        // List of types
        ArrayList<Type> typeList = (ArrayList<Type>) typeDAO.getAll();
        return typeList;
    }

    /**
     * Method getTypeById() gets type by id
     *
     * @param typeId
     * @return type
     */
    public Type getTypeById(int typeId) {
        Type type = typeDAO.getByKey("id", typeId);
        return type;
    }
}
