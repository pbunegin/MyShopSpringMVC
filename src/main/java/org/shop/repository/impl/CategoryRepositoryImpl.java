package org.shop.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.shop.data.Category;
import org.shop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Category getCategoryById(long categoryId) {
        return sessionFactory.getCurrentSession().get(Category.class, categoryId);
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return (Category) sessionFactory.getCurrentSession()
                .createSQLQuery("select * from categories where category_name='"+categoryName+'\'')
                .addEntity(Category.class).uniqueResult();
    }

    @Override
    public List<Category> getCategories() {
        List<Category> categories = sessionFactory.getCurrentSession()
                .createSQLQuery("select * from categories").addEntity(Category.class).list();
        return categories;
    }

    @Override
    public long createCategory(Category category) {
        return (long) sessionFactory.getCurrentSession().save(category);
    }

    @Override
    public void updateCategory(Category category) {
        sessionFactory.getCurrentSession().update(category);
    }

    @Override
    public void deleteCategory(long categoryId) {
        Session session = sessionFactory.getCurrentSession();
        Category category = session.get(Category.class, categoryId);
        session.delete(category);
    }
}
