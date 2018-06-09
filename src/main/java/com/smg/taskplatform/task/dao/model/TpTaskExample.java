package com.smg.taskplatform.task.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TpTaskExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public TpTaskExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria implements Serializable {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andTaskIdIsNull() {
            addCriterion("task_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("task_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(Integer value) {
            addCriterion("task_id =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(Integer value) {
            addCriterion("task_id <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(Integer value) {
            addCriterion("task_id >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_id >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(Integer value) {
            addCriterion("task_id <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(Integer value) {
            addCriterion("task_id <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<Integer> values) {
            addCriterion("task_id in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<Integer> values) {
            addCriterion("task_id not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(Integer value1, Integer value2) {
            addCriterion("task_id between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(Integer value1, Integer value2) {
            addCriterion("task_id not between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andInitiatorIsNull() {
            addCriterion("initiator is null");
            return (Criteria) this;
        }

        public Criteria andInitiatorIsNotNull() {
            addCriterion("initiator is not null");
            return (Criteria) this;
        }

        public Criteria andInitiatorEqualTo(String value) {
            addCriterion("initiator =", value, "initiator");
            return (Criteria) this;
        }

        public Criteria andInitiatorNotEqualTo(String value) {
            addCriterion("initiator <>", value, "initiator");
            return (Criteria) this;
        }

        public Criteria andInitiatorGreaterThan(String value) {
            addCriterion("initiator >", value, "initiator");
            return (Criteria) this;
        }

        public Criteria andInitiatorGreaterThanOrEqualTo(String value) {
            addCriterion("initiator >=", value, "initiator");
            return (Criteria) this;
        }

        public Criteria andInitiatorLessThan(String value) {
            addCriterion("initiator <", value, "initiator");
            return (Criteria) this;
        }

        public Criteria andInitiatorLessThanOrEqualTo(String value) {
            addCriterion("initiator <=", value, "initiator");
            return (Criteria) this;
        }

        public Criteria andInitiatorLike(String value) {
            addCriterion("initiator like", value, "initiator");
            return (Criteria) this;
        }

        public Criteria andInitiatorNotLike(String value) {
            addCriterion("initiator not like", value, "initiator");
            return (Criteria) this;
        }

        public Criteria andInitiatorIn(List<String> values) {
            addCriterion("initiator in", values, "initiator");
            return (Criteria) this;
        }

        public Criteria andInitiatorNotIn(List<String> values) {
            addCriterion("initiator not in", values, "initiator");
            return (Criteria) this;
        }

        public Criteria andInitiatorBetween(String value1, String value2) {
            addCriterion("initiator between", value1, value2, "initiator");
            return (Criteria) this;
        }

        public Criteria andInitiatorNotBetween(String value1, String value2) {
            addCriterion("initiator not between", value1, value2, "initiator");
            return (Criteria) this;
        }

        public Criteria andResponsiblemanIsNull() {
            addCriterion("responsibleman is null");
            return (Criteria) this;
        }

        public Criteria andResponsiblemanIsNotNull() {
            addCriterion("responsibleman is not null");
            return (Criteria) this;
        }

        public Criteria andResponsiblemanEqualTo(String value) {
            addCriterion("responsibleman =", value, "responsibleman");
            return (Criteria) this;
        }

        public Criteria andResponsiblemanNotEqualTo(String value) {
            addCriterion("responsibleman <>", value, "responsibleman");
            return (Criteria) this;
        }

        public Criteria andResponsiblemanGreaterThan(String value) {
            addCriterion("responsibleman >", value, "responsibleman");
            return (Criteria) this;
        }

        public Criteria andResponsiblemanGreaterThanOrEqualTo(String value) {
            addCriterion("responsibleman >=", value, "responsibleman");
            return (Criteria) this;
        }

        public Criteria andResponsiblemanLessThan(String value) {
            addCriterion("responsibleman <", value, "responsibleman");
            return (Criteria) this;
        }

        public Criteria andResponsiblemanLessThanOrEqualTo(String value) {
            addCriterion("responsibleman <=", value, "responsibleman");
            return (Criteria) this;
        }

        public Criteria andResponsiblemanLike(String value) {
            addCriterion("responsibleman like", value, "responsibleman");
            return (Criteria) this;
        }

        public Criteria andResponsiblemanNotLike(String value) {
            addCriterion("responsibleman not like", value, "responsibleman");
            return (Criteria) this;
        }

        public Criteria andResponsiblemanIn(List<String> values) {
            addCriterion("responsibleman in", values, "responsibleman");
            return (Criteria) this;
        }

        public Criteria andResponsiblemanNotIn(List<String> values) {
            addCriterion("responsibleman not in", values, "responsibleman");
            return (Criteria) this;
        }

        public Criteria andResponsiblemanBetween(String value1, String value2) {
            addCriterion("responsibleman between", value1, value2, "responsibleman");
            return (Criteria) this;
        }

        public Criteria andResponsiblemanNotBetween(String value1, String value2) {
            addCriterion("responsibleman not between", value1, value2, "responsibleman");
            return (Criteria) this;
        }

        public Criteria andExecutorIsNull() {
            addCriterion("executor is null");
            return (Criteria) this;
        }

        public Criteria andExecutorIsNotNull() {
            addCriterion("executor is not null");
            return (Criteria) this;
        }

        public Criteria andExecutorEqualTo(String value) {
            addCriterion("executor =", value, "executor");
            return (Criteria) this;
        }

        public Criteria andExecutorNotEqualTo(String value) {
            addCriterion("executor <>", value, "executor");
            return (Criteria) this;
        }

        public Criteria andExecutorGreaterThan(String value) {
            addCriterion("executor >", value, "executor");
            return (Criteria) this;
        }

        public Criteria andExecutorGreaterThanOrEqualTo(String value) {
            addCriterion("executor >=", value, "executor");
            return (Criteria) this;
        }

        public Criteria andExecutorLessThan(String value) {
            addCriterion("executor <", value, "executor");
            return (Criteria) this;
        }

        public Criteria andExecutorLessThanOrEqualTo(String value) {
            addCriterion("executor <=", value, "executor");
            return (Criteria) this;
        }

        public Criteria andExecutorLike(String value) {
            addCriterion("executor like", value, "executor");
            return (Criteria) this;
        }

        public Criteria andExecutorNotLike(String value) {
            addCriterion("executor not like", value, "executor");
            return (Criteria) this;
        }

        public Criteria andExecutorIn(List<String> values) {
            addCriterion("executor in", values, "executor");
            return (Criteria) this;
        }

        public Criteria andExecutorNotIn(List<String> values) {
            addCriterion("executor not in", values, "executor");
            return (Criteria) this;
        }

        public Criteria andExecutorBetween(String value1, String value2) {
            addCriterion("executor between", value1, value2, "executor");
            return (Criteria) this;
        }

        public Criteria andExecutorNotBetween(String value1, String value2) {
            addCriterion("executor not between", value1, value2, "executor");
            return (Criteria) this;
        }

        public Criteria andCcIsNull() {
            addCriterion("cc is null");
            return (Criteria) this;
        }

        public Criteria andCcIsNotNull() {
            addCriterion("cc is not null");
            return (Criteria) this;
        }

        public Criteria andCcEqualTo(String value) {
            addCriterion("cc =", value, "cc");
            return (Criteria) this;
        }

        public Criteria andCcNotEqualTo(String value) {
            addCriterion("cc <>", value, "cc");
            return (Criteria) this;
        }

        public Criteria andCcGreaterThan(String value) {
            addCriterion("cc >", value, "cc");
            return (Criteria) this;
        }

        public Criteria andCcGreaterThanOrEqualTo(String value) {
            addCriterion("cc >=", value, "cc");
            return (Criteria) this;
        }

        public Criteria andCcLessThan(String value) {
            addCriterion("cc <", value, "cc");
            return (Criteria) this;
        }

        public Criteria andCcLessThanOrEqualTo(String value) {
            addCriterion("cc <=", value, "cc");
            return (Criteria) this;
        }

        public Criteria andCcLike(String value) {
            addCriterion("cc like", value, "cc");
            return (Criteria) this;
        }

        public Criteria andCcNotLike(String value) {
            addCriterion("cc not like", value, "cc");
            return (Criteria) this;
        }

        public Criteria andCcIn(List<String> values) {
            addCriterion("cc in", values, "cc");
            return (Criteria) this;
        }

        public Criteria andCcNotIn(List<String> values) {
            addCriterion("cc not in", values, "cc");
            return (Criteria) this;
        }

        public Criteria andCcBetween(String value1, String value2) {
            addCriterion("cc between", value1, value2, "cc");
            return (Criteria) this;
        }

        public Criteria andCcNotBetween(String value1, String value2) {
            addCriterion("cc not between", value1, value2, "cc");
            return (Criteria) this;
        }

        public Criteria andOperatorlistIsNull() {
            addCriterion("operatorlist is null");
            return (Criteria) this;
        }

        public Criteria andOperatorlistIsNotNull() {
            addCriterion("operatorlist is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorlistEqualTo(String value) {
            addCriterion("operatorlist =", value, "operatorlist");
            return (Criteria) this;
        }

        public Criteria andOperatorlistNotEqualTo(String value) {
            addCriterion("operatorlist <>", value, "operatorlist");
            return (Criteria) this;
        }

        public Criteria andOperatorlistGreaterThan(String value) {
            addCriterion("operatorlist >", value, "operatorlist");
            return (Criteria) this;
        }

        public Criteria andOperatorlistGreaterThanOrEqualTo(String value) {
            addCriterion("operatorlist >=", value, "operatorlist");
            return (Criteria) this;
        }

        public Criteria andOperatorlistLessThan(String value) {
            addCriterion("operatorlist <", value, "operatorlist");
            return (Criteria) this;
        }

        public Criteria andOperatorlistLessThanOrEqualTo(String value) {
            addCriterion("operatorlist <=", value, "operatorlist");
            return (Criteria) this;
        }

        public Criteria andOperatorlistLike(String value) {
            addCriterion("operatorlist like", value, "operatorlist");
            return (Criteria) this;
        }

        public Criteria andOperatorlistNotLike(String value) {
            addCriterion("operatorlist not like", value, "operatorlist");
            return (Criteria) this;
        }

        public Criteria andOperatorlistIn(List<String> values) {
            addCriterion("operatorlist in", values, "operatorlist");
            return (Criteria) this;
        }

        public Criteria andOperatorlistNotIn(List<String> values) {
            addCriterion("operatorlist not in", values, "operatorlist");
            return (Criteria) this;
        }

        public Criteria andOperatorlistBetween(String value1, String value2) {
            addCriterion("operatorlist between", value1, value2, "operatorlist");
            return (Criteria) this;
        }

        public Criteria andOperatorlistNotBetween(String value1, String value2) {
            addCriterion("operatorlist not between", value1, value2, "operatorlist");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIsNull() {
            addCriterion("task_type is null");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIsNotNull() {
            addCriterion("task_type is not null");
            return (Criteria) this;
        }

        public Criteria andTaskTypeEqualTo(Byte value) {
            addCriterion("task_type =", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotEqualTo(Byte value) {
            addCriterion("task_type <>", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeGreaterThan(Byte value) {
            addCriterion("task_type >", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("task_type >=", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLessThan(Byte value) {
            addCriterion("task_type <", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLessThanOrEqualTo(Byte value) {
            addCriterion("task_type <=", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIn(List<Byte> values) {
            addCriterion("task_type in", values, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotIn(List<Byte> values) {
            addCriterion("task_type not in", values, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeBetween(Byte value1, Byte value2) {
            addCriterion("task_type between", value1, value2, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("task_type not between", value1, value2, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskSourceIsNull() {
            addCriterion("task_source is null");
            return (Criteria) this;
        }

        public Criteria andTaskSourceIsNotNull() {
            addCriterion("task_source is not null");
            return (Criteria) this;
        }

        public Criteria andTaskSourceEqualTo(Byte value) {
            addCriterion("task_source =", value, "taskSource");
            return (Criteria) this;
        }

        public Criteria andTaskSourceNotEqualTo(Byte value) {
            addCriterion("task_source <>", value, "taskSource");
            return (Criteria) this;
        }

        public Criteria andTaskSourceGreaterThan(Byte value) {
            addCriterion("task_source >", value, "taskSource");
            return (Criteria) this;
        }

        public Criteria andTaskSourceGreaterThanOrEqualTo(Byte value) {
            addCriterion("task_source >=", value, "taskSource");
            return (Criteria) this;
        }

        public Criteria andTaskSourceLessThan(Byte value) {
            addCriterion("task_source <", value, "taskSource");
            return (Criteria) this;
        }

        public Criteria andTaskSourceLessThanOrEqualTo(Byte value) {
            addCriterion("task_source <=", value, "taskSource");
            return (Criteria) this;
        }

        public Criteria andTaskSourceIn(List<Byte> values) {
            addCriterion("task_source in", values, "taskSource");
            return (Criteria) this;
        }

        public Criteria andTaskSourceNotIn(List<Byte> values) {
            addCriterion("task_source not in", values, "taskSource");
            return (Criteria) this;
        }

        public Criteria andTaskSourceBetween(Byte value1, Byte value2) {
            addCriterion("task_source between", value1, value2, "taskSource");
            return (Criteria) this;
        }

        public Criteria andTaskSourceNotBetween(Byte value1, Byte value2) {
            addCriterion("task_source not between", value1, value2, "taskSource");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNull() {
            addCriterion("priority is null");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNotNull() {
            addCriterion("priority is not null");
            return (Criteria) this;
        }

        public Criteria andPriorityEqualTo(Byte value) {
            addCriterion("priority =", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotEqualTo(Byte value) {
            addCriterion("priority <>", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThan(Byte value) {
            addCriterion("priority >", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanOrEqualTo(Byte value) {
            addCriterion("priority >=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThan(Byte value) {
            addCriterion("priority <", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanOrEqualTo(Byte value) {
            addCriterion("priority <=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityIn(List<Byte> values) {
            addCriterion("priority in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotIn(List<Byte> values) {
            addCriterion("priority not in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityBetween(Byte value1, Byte value2) {
            addCriterion("priority between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotBetween(Byte value1, Byte value2) {
            addCriterion("priority not between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNull() {
            addCriterion("starttime is null");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNotNull() {
            addCriterion("starttime is not null");
            return (Criteria) this;
        }

        public Criteria andStarttimeEqualTo(Long value) {
            addCriterion("starttime =", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotEqualTo(Long value) {
            addCriterion("starttime <>", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThan(Long value) {
            addCriterion("starttime >", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThanOrEqualTo(Long value) {
            addCriterion("starttime >=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThan(Long value) {
            addCriterion("starttime <", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThanOrEqualTo(Long value) {
            addCriterion("starttime <=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeIn(List<Long> values) {
            addCriterion("starttime in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotIn(List<Long> values) {
            addCriterion("starttime not in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeBetween(Long value1, Long value2) {
            addCriterion("starttime between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotBetween(Long value1, Long value2) {
            addCriterion("starttime not between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIsNull() {
            addCriterion("task_status is null");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIsNotNull() {
            addCriterion("task_status is not null");
            return (Criteria) this;
        }

        public Criteria andTaskStatusEqualTo(Byte value) {
            addCriterion("task_status =", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotEqualTo(Byte value) {
            addCriterion("task_status <>", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusGreaterThan(Byte value) {
            addCriterion("task_status >", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("task_status >=", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusLessThan(Byte value) {
            addCriterion("task_status <", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusLessThanOrEqualTo(Byte value) {
            addCriterion("task_status <=", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIn(List<Byte> values) {
            addCriterion("task_status in", values, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotIn(List<Byte> values) {
            addCriterion("task_status not in", values, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusBetween(Byte value1, Byte value2) {
            addCriterion("task_status between", value1, value2, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("task_status not between", value1, value2, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNull() {
            addCriterion("endtime is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNotNull() {
            addCriterion("endtime is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeEqualTo(Long value) {
            addCriterion("endtime =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(Long value) {
            addCriterion("endtime <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(Long value) {
            addCriterion("endtime >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(Long value) {
            addCriterion("endtime >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(Long value) {
            addCriterion("endtime <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(Long value) {
            addCriterion("endtime <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<Long> values) {
            addCriterion("endtime in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<Long> values) {
            addCriterion("endtime not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(Long value1, Long value2) {
            addCriterion("endtime between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(Long value1, Long value2) {
            addCriterion("endtime not between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andTaskAssociationIsNull() {
            addCriterion("task_association is null");
            return (Criteria) this;
        }

        public Criteria andTaskAssociationIsNotNull() {
            addCriterion("task_association is not null");
            return (Criteria) this;
        }

        public Criteria andTaskAssociationEqualTo(String value) {
            addCriterion("task_association =", value, "taskAssociation");
            return (Criteria) this;
        }

        public Criteria andTaskAssociationNotEqualTo(String value) {
            addCriterion("task_association <>", value, "taskAssociation");
            return (Criteria) this;
        }

        public Criteria andTaskAssociationGreaterThan(String value) {
            addCriterion("task_association >", value, "taskAssociation");
            return (Criteria) this;
        }

        public Criteria andTaskAssociationGreaterThanOrEqualTo(String value) {
            addCriterion("task_association >=", value, "taskAssociation");
            return (Criteria) this;
        }

        public Criteria andTaskAssociationLessThan(String value) {
            addCriterion("task_association <", value, "taskAssociation");
            return (Criteria) this;
        }

        public Criteria andTaskAssociationLessThanOrEqualTo(String value) {
            addCriterion("task_association <=", value, "taskAssociation");
            return (Criteria) this;
        }

        public Criteria andTaskAssociationLike(String value) {
            addCriterion("task_association like", value, "taskAssociation");
            return (Criteria) this;
        }

        public Criteria andTaskAssociationNotLike(String value) {
            addCriterion("task_association not like", value, "taskAssociation");
            return (Criteria) this;
        }

        public Criteria andTaskAssociationIn(List<String> values) {
            addCriterion("task_association in", values, "taskAssociation");
            return (Criteria) this;
        }

        public Criteria andTaskAssociationNotIn(List<String> values) {
            addCriterion("task_association not in", values, "taskAssociation");
            return (Criteria) this;
        }

        public Criteria andTaskAssociationBetween(String value1, String value2) {
            addCriterion("task_association between", value1, value2, "taskAssociation");
            return (Criteria) this;
        }

        public Criteria andTaskAssociationNotBetween(String value1, String value2) {
            addCriterion("task_association not between", value1, value2, "taskAssociation");
            return (Criteria) this;
        }

        public Criteria andSystemAssociationIsNull() {
            addCriterion("system_association is null");
            return (Criteria) this;
        }

        public Criteria andSystemAssociationIsNotNull() {
            addCriterion("system_association is not null");
            return (Criteria) this;
        }

        public Criteria andSystemAssociationEqualTo(String value) {
            addCriterion("system_association =", value, "systemAssociation");
            return (Criteria) this;
        }

        public Criteria andSystemAssociationNotEqualTo(String value) {
            addCriterion("system_association <>", value, "systemAssociation");
            return (Criteria) this;
        }

        public Criteria andSystemAssociationGreaterThan(String value) {
            addCriterion("system_association >", value, "systemAssociation");
            return (Criteria) this;
        }

        public Criteria andSystemAssociationGreaterThanOrEqualTo(String value) {
            addCriterion("system_association >=", value, "systemAssociation");
            return (Criteria) this;
        }

        public Criteria andSystemAssociationLessThan(String value) {
            addCriterion("system_association <", value, "systemAssociation");
            return (Criteria) this;
        }

        public Criteria andSystemAssociationLessThanOrEqualTo(String value) {
            addCriterion("system_association <=", value, "systemAssociation");
            return (Criteria) this;
        }

        public Criteria andSystemAssociationLike(String value) {
            addCriterion("system_association like", value, "systemAssociation");
            return (Criteria) this;
        }

        public Criteria andSystemAssociationNotLike(String value) {
            addCriterion("system_association not like", value, "systemAssociation");
            return (Criteria) this;
        }

        public Criteria andSystemAssociationIn(List<String> values) {
            addCriterion("system_association in", values, "systemAssociation");
            return (Criteria) this;
        }

        public Criteria andSystemAssociationNotIn(List<String> values) {
            addCriterion("system_association not in", values, "systemAssociation");
            return (Criteria) this;
        }

        public Criteria andSystemAssociationBetween(String value1, String value2) {
            addCriterion("system_association between", value1, value2, "systemAssociation");
            return (Criteria) this;
        }

        public Criteria andSystemAssociationNotBetween(String value1, String value2) {
            addCriterion("system_association not between", value1, value2, "systemAssociation");
            return (Criteria) this;
        }

        public Criteria andServerAssociationIsNull() {
            addCriterion("server_association is null");
            return (Criteria) this;
        }

        public Criteria andServerAssociationIsNotNull() {
            addCriterion("server_association is not null");
            return (Criteria) this;
        }

        public Criteria andServerAssociationEqualTo(String value) {
            addCriterion("server_association =", value, "serverAssociation");
            return (Criteria) this;
        }

        public Criteria andServerAssociationNotEqualTo(String value) {
            addCriterion("server_association <>", value, "serverAssociation");
            return (Criteria) this;
        }

        public Criteria andServerAssociationGreaterThan(String value) {
            addCriterion("server_association >", value, "serverAssociation");
            return (Criteria) this;
        }

        public Criteria andServerAssociationGreaterThanOrEqualTo(String value) {
            addCriterion("server_association >=", value, "serverAssociation");
            return (Criteria) this;
        }

        public Criteria andServerAssociationLessThan(String value) {
            addCriterion("server_association <", value, "serverAssociation");
            return (Criteria) this;
        }

        public Criteria andServerAssociationLessThanOrEqualTo(String value) {
            addCriterion("server_association <=", value, "serverAssociation");
            return (Criteria) this;
        }

        public Criteria andServerAssociationLike(String value) {
            addCriterion("server_association like", value, "serverAssociation");
            return (Criteria) this;
        }

        public Criteria andServerAssociationNotLike(String value) {
            addCriterion("server_association not like", value, "serverAssociation");
            return (Criteria) this;
        }

        public Criteria andServerAssociationIn(List<String> values) {
            addCriterion("server_association in", values, "serverAssociation");
            return (Criteria) this;
        }

        public Criteria andServerAssociationNotIn(List<String> values) {
            addCriterion("server_association not in", values, "serverAssociation");
            return (Criteria) this;
        }

        public Criteria andServerAssociationBetween(String value1, String value2) {
            addCriterion("server_association between", value1, value2, "serverAssociation");
            return (Criteria) this;
        }

        public Criteria andServerAssociationNotBetween(String value1, String value2) {
            addCriterion("server_association not between", value1, value2, "serverAssociation");
            return (Criteria) this;
        }

        public Criteria andReportingCycleIsNull() {
            addCriterion("reporting_cycle is null");
            return (Criteria) this;
        }

        public Criteria andReportingCycleIsNotNull() {
            addCriterion("reporting_cycle is not null");
            return (Criteria) this;
        }

        public Criteria andReportingCycleEqualTo(Byte value) {
            addCriterion("reporting_cycle =", value, "reportingCycle");
            return (Criteria) this;
        }

        public Criteria andReportingCycleNotEqualTo(Byte value) {
            addCriterion("reporting_cycle <>", value, "reportingCycle");
            return (Criteria) this;
        }

        public Criteria andReportingCycleGreaterThan(Byte value) {
            addCriterion("reporting_cycle >", value, "reportingCycle");
            return (Criteria) this;
        }

        public Criteria andReportingCycleGreaterThanOrEqualTo(Byte value) {
            addCriterion("reporting_cycle >=", value, "reportingCycle");
            return (Criteria) this;
        }

        public Criteria andReportingCycleLessThan(Byte value) {
            addCriterion("reporting_cycle <", value, "reportingCycle");
            return (Criteria) this;
        }

        public Criteria andReportingCycleLessThanOrEqualTo(Byte value) {
            addCriterion("reporting_cycle <=", value, "reportingCycle");
            return (Criteria) this;
        }

        public Criteria andReportingCycleIn(List<Byte> values) {
            addCriterion("reporting_cycle in", values, "reportingCycle");
            return (Criteria) this;
        }

        public Criteria andReportingCycleNotIn(List<Byte> values) {
            addCriterion("reporting_cycle not in", values, "reportingCycle");
            return (Criteria) this;
        }

        public Criteria andReportingCycleBetween(Byte value1, Byte value2) {
            addCriterion("reporting_cycle between", value1, value2, "reportingCycle");
            return (Criteria) this;
        }

        public Criteria andReportingCycleNotBetween(Byte value1, Byte value2) {
            addCriterion("reporting_cycle not between", value1, value2, "reportingCycle");
            return (Criteria) this;
        }

        public Criteria andSummaryIsNull() {
            addCriterion("summary is null");
            return (Criteria) this;
        }

        public Criteria andSummaryIsNotNull() {
            addCriterion("summary is not null");
            return (Criteria) this;
        }

        public Criteria andSummaryEqualTo(String value) {
            addCriterion("summary =", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotEqualTo(String value) {
            addCriterion("summary <>", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryGreaterThan(String value) {
            addCriterion("summary >", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryGreaterThanOrEqualTo(String value) {
            addCriterion("summary >=", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryLessThan(String value) {
            addCriterion("summary <", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryLessThanOrEqualTo(String value) {
            addCriterion("summary <=", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryLike(String value) {
            addCriterion("summary like", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotLike(String value) {
            addCriterion("summary not like", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryIn(List<String> values) {
            addCriterion("summary in", values, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotIn(List<String> values) {
            addCriterion("summary not in", values, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryBetween(String value1, String value2) {
            addCriterion("summary between", value1, value2, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotBetween(String value1, String value2) {
            addCriterion("summary not between", value1, value2, "summary");
            return (Criteria) this;
        }

        public Criteria andCtimeIsNull() {
            addCriterion("ctime is null");
            return (Criteria) this;
        }

        public Criteria andCtimeIsNotNull() {
            addCriterion("ctime is not null");
            return (Criteria) this;
        }

        public Criteria andCtimeEqualTo(Long value) {
            addCriterion("ctime =", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotEqualTo(Long value) {
            addCriterion("ctime <>", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeGreaterThan(Long value) {
            addCriterion("ctime >", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeGreaterThanOrEqualTo(Long value) {
            addCriterion("ctime >=", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeLessThan(Long value) {
            addCriterion("ctime <", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeLessThanOrEqualTo(Long value) {
            addCriterion("ctime <=", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeIn(List<Long> values) {
            addCriterion("ctime in", values, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotIn(List<Long> values) {
            addCriterion("ctime not in", values, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeBetween(Long value1, Long value2) {
            addCriterion("ctime between", value1, value2, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotBetween(Long value1, Long value2) {
            addCriterion("ctime not between", value1, value2, "ctime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}