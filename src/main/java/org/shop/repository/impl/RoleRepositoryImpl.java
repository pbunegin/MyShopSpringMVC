package org.shop.repository.impl;

import org.hibernate.SessionFactory;
import org.shop.data.UserRole;
import org.shop.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class RoleRepositoryImpl implements RoleRepository {
    private final static Logger LOGGER = LoggerFactory.getLogger(RoleRepositoryImpl.class);

    @Autowired
    private DataSource dataSource;

    @Override
    public Long createRole(UserRole role) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(
                    "insert into roles(user_role, description) values(?,?)");
            preparedStatement.setString(1, role.getRoleName());
            preparedStatement.setString(2, role.getDescription());
            if (preparedStatement.executeUpdate() > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getLong("role_id");
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getSQLState());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getSQLState());
            }
        }
        return null;
    }

    @Override
    public UserRole getRoleById(Long id) {
        UserRole userRole = new UserRole();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(
                    "select * from roles where role_id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            userRole.setId(resultSet.getLong("role_id"));
            userRole.setRoleName(resultSet.getString("user_role"));
            userRole.setDescription("description");
        } catch (SQLException e) {
            LOGGER.error(e.getSQLState());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getSQLState());
            }
        }
        return userRole;
    }

    @Override
    public void updateRole(UserRole role) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(
                    "insert into roles(role_id, user_role, description) values(?,?,?)");
            preparedStatement.setLong(1, role.getId());
            preparedStatement.setString(2, role.getRoleName());
            preparedStatement.setString(3, role.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getSQLState());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getSQLState());
            }
        }
    }

    @Override
    public List<UserRole> getRoles() {
        UserRole userRole = new UserRole();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from roles where role_id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            userRole.setId(resultSet.getLong("role_id"));
            userRole.setRoleName(resultSet.getString("user_role"));
            userRole.setDescription("description");
        } catch (SQLException e) {
            LOGGER.error(e.getSQLState());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return userRole;
    }
}
