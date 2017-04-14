package com.starschina.connect.heyi;

import com.starschina.connect.heyi.Entity.HYCategoryList;
import com.starschina.connect.heyi.Entity.HYDeviceAuth;
import com.starschina.connect.heyi.Entity.HYDeviceInit;
import com.starschina.connect.heyi.Entity.HYNavigationInfo;
import com.starschina.connect.heyi.Entity.HYProductList;
import com.starschina.connect.heyi.Entity.HYRecommendInfo;
import com.starschina.connect.heyi.Entity.HYSeriesInfo;
import com.starschina.connect.heyi.Entity.HYSpecialPackageList;
import com.starschina.connect.heyi.Entity.HYSpecialProductList;

import org.junit.Before;
import org.junit.Test;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/1/5.
 */
public class DataSourceImplTest {

    IHYDataSource dataSource;

    @Before
    public void setUp() throws Exception {
        dataSource = new HYDataSourceImpl();
    }

    @Test
    public void deviceAuthTest() throws Exception {
        dataSource.deviceAuth().subscribe(new Subscriber<HYDeviceAuth>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {
                System.out.print("error:"+e.getMessage());
            }

            @Override
            public void onNext(HYDeviceAuth deviceAuth) {
                System.out.print("auth:"+deviceAuth.getRetCode());
            }
        });
    }

    @Test
    public void deviceInitTest() throws Exception {
        dataSource.deviceInit().subscribe(new Subscriber<HYDeviceInit>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {
                System.out.print("error:"+e.getMessage());
            }

            @Override
            public void onNext(HYDeviceInit deviceInit) {
                System.out.print("deviceInit:"+deviceInit.getRetCode());
            }
        });
    }

    @Test
    public void getNavigationInfoTest() throws Exception {
        String serviceComboCode = "1100121022001790197750001";
        dataSource.getNavigationInfo(serviceComboCode).subscribe(new Subscriber<HYNavigationInfo>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {
                System.out.print("error:"+e.getMessage());
            }

            @Override
            public void onNext(HYNavigationInfo navigationInfo) {
                System.out.print("navigationInfo:"+navigationInfo.getRetCode());
            }
        });
    }

    @Test
    public void getRecommendInfoTest() throws Exception {
        String recommendCode = "1100070023128885450430004";
        dataSource.getRecommendInfo(recommendCode).subscribe(new Subscriber<HYRecommendInfo>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {
                System.out.print("error:"+e.getMessage());
            }

            @Override
            public void onNext(HYRecommendInfo recommendInfo) {
                System.out.print("recommendInfo:"+recommendInfo.getRetCode());
            }
        });
    }

    @Test
    public void getCategoryListTest() throws Exception {
        String serviceGroupCode = "1100122023691085155710001";
        dataSource.getCategoryList(serviceGroupCode).subscribe(new Subscriber<HYCategoryList>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {
                System.out.print("error:"+e.getMessage());
            }

            @Override
            public void onNext(HYCategoryList categoryList) {
                System.out.print("categoryList:"+categoryList.getRetCode());
                System.out.print("\ncategoryList size:"+categoryList.getRetMsg().size());
            }
        });
    }

    @Test
    public void getProductListTest() throws Exception {
        String serviceGroupCode = "1100122023691085155710001";
        String packageCodes = "2100130022971772462150150";
        int pageLimit = 5;
        int pageNum = 0;

        dataSource.getProductList(serviceGroupCode, packageCodes, pageLimit, pageNum)
                .subscribe(new Subscriber<HYProductList>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        System.out.print("error:"+e.getMessage());
                    }

                    @Override
                    public void onNext(HYProductList productList) {
                        System.out.print("productList:"+productList.getRetCode());
                    }
                });
    }

    @Test
    public void getPackageListTest() throws Exception {
        String serviceGroupCode = "1100122023691085155710001";
        String packageCodes = "2100130012926427367920002,2100130014476418449470002,2100130022057346939590013,2100130022057355867670014,2100130022057363363570018,2100130023147359623490016,2100130023147379640330018,2100130023874077122070761,2100130023874080803640762";

        dataSource.getPackageList(serviceGroupCode, packageCodes)
                .subscribe(new Subscriber<HYSpecialPackageList>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        System.out.print("error:"+e.getMessage());
                    }

                    @Override
                    public void onNext(HYSpecialPackageList hySpecialPackageList) {
                        System.out.print("productList:"+hySpecialPackageList.getRetCode());
                    }
                });
    }

    @Test
    public void getPackageInfoByCodeTest() throws Exception {
        String serviceGroupCode = "1100122023691085155710001";
        String packageCode = "2100130014476418449470002";
        int pageLimit = 5;
        int pageNum = 0;

        dataSource.getPackageInfoByCode(serviceGroupCode, packageCode, pageLimit, pageNum)
                .subscribe(new Subscriber<HYSpecialProductList>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        System.out.print("error:"+e.getMessage());
                    }

                    @Override
                    public void onNext(HYSpecialProductList specialProductList) {
                        System.out.print("specialProductList:"+specialProductList.getRetCode());
                    }
                });
    }

    @Test
    public void getSeriesInfoByCodeTest() throws Exception {
        String domainCodes = "2100090023975707532900011";
        String serviceGroupCode = "1100122023691085155710001";
        String userCode = "1300110014882443382910012";
        String productCode = "2100140023646354245640027";

        dataSource.getSeriesInfoByCode(domainCodes, serviceGroupCode, userCode, productCode)
                .subscribe(new Subscriber<HYSeriesInfo>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        System.out.print("error:"+e.getMessage());
                    }

                    @Override
                    public void onNext(HYSeriesInfo seriesInfo) {
                        System.out.print("seriesInfo:"+seriesInfo.getRetMsg().getSeriesName());
                    }
                });
    }

    @Test
    public void getRelatedRecommendListTest() throws Exception {
        String serviceGroupCode = "1100122023691085155710001";
        String userCode = "1300110014882443382910012";
        String productCode = "2100140023646354245640027";
        int pageLimit = 5;
        int pageNum = 2;

        dataSource.getRelatedRecommendList(serviceGroupCode, userCode, productCode, pageLimit, pageNum)
                .subscribe(new Subscriber<HYProductList>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        System.out.print("error:"+e.getMessage());
                    }

                    @Override
                    public void onNext(HYProductList productList) {
                        System.out.print("productList:"+productList.getRetCode());
                    }
                });
    }

    @Test
    public void getCommodityListBySerachTest() throws Exception {
        String serviceGroupCode = "1100122023691085155710001";
        String userCode = "1300110014882443382910012";
        String keyword = "汽车";
        String year = "";//"2016";
        String areaCode = "";//"美国";
        String contentType = "";//"电影";
        int pageLimit = 1;
        int pageNum = 5;

        dataSource.getCommodityListBySerach(serviceGroupCode, userCode, keyword, year, areaCode, contentType, pageLimit, pageNum)
                .subscribe(new Subscriber<HYProductList>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        System.out.print("error:"+e.getMessage());
                    }

                    @Override
                    public void onNext(HYProductList productList) {
                        System.out.print("productList:"+productList.getRetCode());
                    }
                });
    }
}