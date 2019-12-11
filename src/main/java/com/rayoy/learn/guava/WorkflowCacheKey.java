package com.rayoy.learn.guava;

import java.util.Objects;

/**
 * @createTime 2019-04-22 15:15
 */
public class WorkflowCacheKey {
    public Long id;
    public String name;
    public String avatar;

    public WorkflowCacheKey(Long id, String name, String avatar) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WorkflowCacheKey that = (WorkflowCacheKey) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(avatar, that.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, avatar);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WorkflowCacheKey{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", avatar='").append(avatar).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
