package com.asiainfo.integretion.o2p.servicemigration.smo;

import javax.jms.IllegalStateException;

import com.asiainfo.integretion.o2p.servicemigration.domain.BasedBean;

public interface ICheckExistService {

	boolean checkExist(BasedBean targetObj, BasedBean rootBean, Integer tenantId) throws IllegalStateException;

}
