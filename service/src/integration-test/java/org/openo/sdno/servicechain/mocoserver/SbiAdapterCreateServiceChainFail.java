/*
 * Copyright 2016 Huawei Technologies Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openo.sdno.servicechain.mocoserver;

import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.model.servicechain.ServiceChainPath;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.testframework.http.model.HttpResponse;
import org.openo.sdno.testframework.http.model.HttpRquestResponse;
import org.openo.sdno.testframework.moco.MocoHttpServer;
import org.openo.sdno.testframework.moco.responsehandler.MocoResponseHandler;

/**
 * SbiAdapterCreateServiceChainFail test class.<br>
 * 
 * @author
 * @version SDNO 0.5 August 22, 2016
 */
public class SbiAdapterCreateServiceChainFail extends MocoHttpServer {

    private int status = 500;

    private static final String CREATE_SERVICECHAIN_FAIL_SBI_FILE =
            "src/integration-test/resources/sbiadapter/createservicechainfail.json";

    private static final String DELETE_SERVICECHAINC_SUCCESS_SBI_FILE =
            "src/integration-test/resources/sbiadapter/deleteservicechainsuccess.json";

    public SbiAdapterCreateServiceChainFail() {

    }

    public void setCreateErrStatus(int status) {
        this.status = status;
    }

    @Override
    public void addRequestResponsePairs() {

        this.addRequestResponsePair(CREATE_SERVICECHAIN_FAIL_SBI_FILE, new CreateServiceChainFailSbiResponseHandler());

        this.addRequestResponsePair(DELETE_SERVICECHAINC_SUCCESS_SBI_FILE);

    }

    private class CreateServiceChainFailSbiResponseHandler extends MocoResponseHandler {

        @Override
        public void processRequestandResponse(HttpRquestResponse httpObject) {

            HttpResponse httpResponse = httpObject.getResponse();

            ResultRsp<ServiceChainPath> newResult = new ResultRsp<ServiceChainPath>(ErrorCode.OVERLAYVPN_FAILED);
            newResult.setHttpCode(status);

            httpResponse.setStatus(status);
            httpResponse.setData(JsonUtil.toJson(newResult));
        }
    }

}
