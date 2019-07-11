package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LabelService {

    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部标签
     * @return
     */
    public List<Label> findAll(){
        return labelDao.findAll();
    }

    /**
     * 根据ID查询标签
     * @param id
     * @return
     */
    public Label findById(String id){
        return labelDao.findById(id).get();
    }

    /**
     * 增加标签
     * @param label
     */
    public void add(Label label){
        // 设置ID
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    /**
     * 修改标签
     * @param label
     */
    public void update(Label label){
        labelDao.save(label);
    }

    /**
     * 删除标签
     * @param id
     */
    public void deleteById(String id){
        labelDao.deleteById(id);
    }

    /**
     * 根据查询条件查询d
     * @param label 封装查询条件类
     * @return
     */
    public List<Label> findSearch(Label label) {
        return labelDao.findAll(new Specification<Label>() {
            /**
             * @param root 条件参数的列名 where 列名=值
             * @param criteriaQuery 查询关键字 group by ,order by 等
             * @param criteriaBuilder 用来封装条件的，直接返回null，表示不需要任何条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if(label.getLabelname() != null && !"".equals(label.getLabelname())){
                    Predicate labelname = criteriaBuilder.like(root.get("labelname").as(String.class), "%"+label.getLabelname()+"%");
                    list.add(labelname);
                }
                if(label.getState() != null && !"".equals(label.getState())){
                    Predicate state = criteriaBuilder.equal(root.get("state").as(String.class), label.getState());
                    list.add(state);
                }
                // 封装查询条件数组
                Predicate[] parr = new Predicate[list.size()];
                list.toArray(parr);
                return criteriaBuilder.and(parr);
            }
        });
    }

    public Page<Label> pageQuery(Label label, int page, int size) {
        // 封装一个分页对象
        Pageable pageable = PageRequest.of(page-1,size);
        return labelDao.findAll(new Specification<Label>() {
            /**
             * @param root 条件参数的列名 where 列名=值
             * @param criteriaQuery 查询关键字 group by ,order by 等
             * @param criteriaBuilder 用来封装条件的，直接返回null，表示不需要任何条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if(label.getLabelname() != null && !"".equals(label.getLabelname())){
                    Predicate labelname = criteriaBuilder.like(root.get("labelname").as(String.class), "%"+label.getLabelname()+"%");
                    list.add(labelname);
                }
                if(label.getState() != null && !"".equals(label.getState())){
                    Predicate state = criteriaBuilder.equal(root.get("state").as(String.class), label.getState());
                    list.add(state);
                }
                // 封装查询条件数组
                Predicate[] parr = new Predicate[list.size()];
                list.toArray(parr);
                return criteriaBuilder.and(parr);
            }
        },pageable);
    }
}
