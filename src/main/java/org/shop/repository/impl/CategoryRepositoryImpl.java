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
    public Category getCategoryById(Long categoryId) {
        return sessionFactory.getCurrentSession().get(Category.class, categoryId);
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return (Category) sessionFactory.getCurrentSession()
                .createQuery("from Category where category_name='"+categoryName+'\'').uniqueResult();
    }

    @Override
    public List<Category> getCategories() {
        return sessionFactory.getCurrentSession().createQuery("from Category").list();
    }

    @Override
    public Long createCategory(Category category) {
        return (Long) sessionFactory.getCurrentSession().save(category);
    }

    @Override
    public void updateCategory(Category category) {
        sessionFactory.getCurrentSession().merge(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        sessionFactory.getCurrentSession().delete(getCategoryById(categoryId));
    }
}
