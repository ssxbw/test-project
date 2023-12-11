package com.payment.service.serviceIml;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.payment.mapper.ArbitrationMapper;
import com.payment.pojo.Arbitration;
import com.payment.service.ArbitrationService;
import com.payment.vo.ArbitrationInput;
import com.payment.vo.ResultM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j//lombok
@Transactional
public class ArbitrationServiceImpl extends ServiceImpl<ArbitrationMapper, Arbitration> implements ArbitrationService {
    @Autowired
    ArbitrationMapper arbitrationMapper;
    @Override
    public ResultM AddArbitration(ArbitrationInput input) {
        log.info("arbitrationInput={}",input);
        Arbitration arbitration=new Arbitration();
        arbitration.setArbitrationTime(input.getArbitrationtime());
        arbitration.setArbitratedUserId(input.getUserid());
        arbitration.setStartTime(System.currentTimeMillis());
        arbitration.setEndTime(System.currentTimeMillis()+input.getArbitrationtime());
        try {
            arbitrationMapper.insert(arbitration);
            log.info("arbitration inserted={}",arbitration);
        }catch (Exception e){
            return ResultM.fail(e.getMessage(),null);
        }
        return ResultM.success("successful arbitrate user "+input.getUserid());
    }

    @Override
    public ResultM CheckArbitration(int UserId) {
        QueryWrapper<Arbitration> wrapper=new QueryWrapper<>();
        wrapper.eq("arbitrated_user_id",UserId);
        wrapper.gt("end_time",System.currentTimeMillis());
        List<Arbitration>arbitrations=arbitrationMapper.selectList(wrapper);
        if(arbitrations.isEmpty())
            return ResultM.success("successful result",null);
        else {
            Arbitration arbitration0=arbitrations.get(0);
            for(Arbitration arbitration:arbitrations){
                if (arbitration.getEndTime()>arbitration0.getEndTime()){
                    arbitration0=arbitration;
                }
            }
            Long endTime=arbitration0.getEndTime();
            return ResultM.fail("being blocked",endTime);
        }
    }
}
