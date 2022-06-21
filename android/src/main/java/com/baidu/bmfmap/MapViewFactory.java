package android.src.main.java.com.baidu.bmfmap;

import java.util.Map;

import com.baidu.bmfmap.map.FlutterMapView;
import com.baidu.bmfmap.utils.Constants;
import com.baidu.bmfmap.utils.Env;
import com.baidu.bmfmap.utils.converter.FlutterDataConveter;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;

public class MapViewFactory extends PlatformViewFactory {

    private static final String TAG = "ViewFactory";
    private final BinaryMessenger mMessenger;
    private final LifecycleProxy mLifecycleProxy;

    /**
     * @param messenger the codec used to decode the args parameter of {@link #create}.
     */
    public MapViewFactory(BinaryMessenger messenger, LifecycleProxy lifecycleProxy) {
        super(StandardMessageCodec.INSTANCE);
        if(Env.DEBUG){
            Log.d(TAG, "ViewFactory");
        }
        mMessenger = messenger;
        mLifecycleProxy = lifecycleProxy;
    }

    @Override
    public PlatformView create(Context context, int viewId, Object args) {
        if(Env.DEBUG){
            Log.d(TAG, "MapViewFactory create");
        }

        BMFMapController bmfMapController = buildBMFMapController(context, viewId, args);
        return new FlutterMapView(context, bmfMapController, mLifecycleProxy);
    }

    private BMFMapController buildBMFMapController(Context context, int viewId, Object args) {
        Map<?, ?> params = FlutterDataConveter.toMap(args);
        final BMFMapBuilder builder = new BMFMapBuilder();

        BaiduMapOptions baiduMapOptions = FlutterDataConveter.toBaiduMapOptions(args);

        // 3.1.0新增 设置地图语言类型
        if (params.containsKey("languageType")) {
            Integer languageType = FlutterDataConveter.toInt(params.get("languageType"));
            if (languageType != null) {
                builder.languageType(languageType);
            }
        }

        // 3.1.0新增 设置地图字体大小
        if (params.containsKey("fontSizeLevel")) {
            Integer fontSizeLevel = FlutterDataConveter.toInt(params.get("fontSizeLevel"));
            if (fontSizeLevel != null) {
                builder.fontSizeLevel(fontSizeLevel);
            }
        }

        if (params.containsKey("minZoomLevel")) {
            Integer minZoomLevel = FlutterDataConveter.toInt(params.get("minZoomLevel"));
            if (minZoomLevel != null) {
                builder.minZoomLevel(minZoomLevel);
            }
        } 
        
        if (params.containsKey("zoomLevel")) {
            Float zoomLevel = FlutterDataConveter.toFloat(params.get("zoomLevel"));
            if (null != zoomLevel) {
                builder.zoomLevel(zoomLevel);
            }
        }

        if (params.containsKey("center")) {
            Map<String, Object> mapCenter = (Map<String, Object>) FlutterDataConveter.toMap(params.get("center"));
            if (null != mapCenter) {
                LatLng  center = FlutterDataConveter.mapToLatlng(mapCenter);
                if (null != center) {
                    builder.center(center);
                }
            }
        }

        if (params.containsKey("maxZoomLevel")) {
            Integer maxZoomLevel = FlutterDataConveter.toInt(params.get("maxZoomLevel"));
            if (maxZoomLevel != null) {
                builder.maxZoomLevel(maxZoomLevel);
            }
        }

        if (params.containsKey("compassEnabled")) {
            Boolean compassEnabled = FlutterDataConveter.toBoolean(params.get("compassEnabled"));
            if (compassEnabled != null) {
                builder.compassEnabled(compassEnabled);
            }
        }

        if (params.containsKey("buildingsEnabled")) {
            Boolean buildingsEnabled =
                    FlutterDataConveter.toBoolean(params.get("buildingsEnabled"));
            if (buildingsEnabled != null) {
                builder.buildingsEnabled(buildingsEnabled);
            }
        }

        if (params.containsKey("showMapPoi")) {
            Boolean showMapPoi = FlutterDataConveter.toBoolean(params.get("showMapPoi"));
            if (showMapPoi != null) {
                builder.showMapPoi(showMapPoi);
            }
        }

        if (params.containsKey("trafficEnabled")) {
            Boolean trafficEnabled = FlutterDataConveter.toBoolean(params.get("trafficEnabled"));
            if (trafficEnabled != null) {
                builder.trafficEnabled(trafficEnabled);
            }
        }

        if (params.containsKey("rotateEnabled")) {
            Boolean rotateEnabled =
                    FlutterDataConveter.toBoolean(params.get("rotateEnabled"));
            if (rotateEnabled != null) {
                builder.rotateEnabled(rotateEnabled);
            }
        }

        if (params.containsKey("scrollEnabled")) {
            Boolean scrollEnabled =
                    FlutterDataConveter.toBoolean(params.get("scrollEnabled"));
            if (scrollEnabled != null) {
                builder.scrollEnabled(scrollEnabled);
            }
        }

        if (params.containsKey("overlookEnabled")) {
            Boolean overlookEnabled =
                    FlutterDataConveter.toBoolean(params.get("overlookEnabled"));
            if (overlookEnabled != null) {
                builder.overlookEnabled(overlookEnabled);
            }
        }

        if (params.containsKey("zoomEnabled")) {
            Boolean zoomEnabled =
                    FlutterDataConveter.toBoolean(params.get("zoomEnabled"));
            if (zoomEnabled != null) {
                builder.zoomEnabled(zoomEnabled);
            }
        }

        if (params.containsKey("showZoomControl")) {
            Boolean showZoomControl =
                    FlutterDataConveter.toBoolean(params.get("showZoomControl"));
            if (showZoomControl != null) {
                builder.showZoomControl(showZoomControl);
            }
        }

        if (params.containsKey("logoPosition")) {
            Integer logoPosition = FlutterDataConveter.toInt(params.get("logoPosition"));
            if (logoPosition != null) {
                builder.logoPosition(LogoPosition.values()[logoPosition.intValue()]);
            }
        }

        if (params.containsKey("visibleMapBounds")) {
            Map<String, Object>  visibleMapBounds =
                    (Map<String, Object>) FlutterDataConveter.toMap(params.get("visibleMapBounds"));
            if (visibleMapBounds != null) {
                LatLngBounds latLngBounds = FlutterDataConveter.mapToLatlngBounds(visibleMapBounds);
                if (null != latLngBounds) {
                    builder.visibleMapBounds(latLngBounds);
                }
            }
        }

        if (params.containsKey("baiduHeatMapEnabled")) {
            Boolean baiduHeatMapEnabled =
                    FlutterDataConveter.toBoolean(params.get("baiduHeatMapEnabled"));
            if (baiduHeatMapEnabled != null) {
                builder.baiduHeatMapEnabled(baiduHeatMapEnabled);
            }
        }

        if (params.containsKey("gesturesEnabled")) {
            Boolean gesturesEnabled = FlutterDataConveter.toBoolean(params.get("gesturesEnabled"));
            if (gesturesEnabled != null) {
                builder.allGesturesEnabled(gesturesEnabled);
            }
        }

        if (params.containsKey("zoomEnabledWithTap")) {
            Boolean zoomEnabledWithTap =
                    FlutterDataConveter.toBoolean(params.get("zoomEnabledWithTap"));
            if (zoomEnabledWithTap != null) {
                builder.zoomEnabledWithTap(zoomEnabledWithTap);
            }
        }

        if (params.containsKey("zoomEnabledWithDoubleClick")) {
            Boolean zoomEnabledWithDoubleClick =
                    FlutterDataConveter.toBoolean(params.get("zoomEnabledWithDoubleClick"));
            if (zoomEnabledWithDoubleClick != null) {
                builder.zoomEnabledWithDoubleClick(zoomEnabledWithDoubleClick);
            }
        }

        if (params.containsKey("changeCenterWithDoubleTouchPointEnabled")) {
            Boolean changeCenterWithDoubleTouchPointEnabled = FlutterDataConveter
                    .toBoolean(params.get("changeCenterWithDoubleTouchPointEnabled"));
            if (changeCenterWithDoubleTouchPointEnabled != null) {
                builder.changeCenterWithDoubleTouchPointEnabled(changeCenterWithDoubleTouchPointEnabled);
            }
        }

        if (params.containsKey("showMapScaleBar")) {
            Boolean showMapScaleBar = FlutterDataConveter.toBoolean(params.get("showMapScaleBar"));
            if (showMapScaleBar != null) {
                builder.showMapScaleBar(showMapScaleBar);
            }
        }

        if (params.containsKey("baseIndoorMapEnabled")) {
            Boolean baseIndoorMapEnabled =
                    FlutterDataConveter.toBoolean(params.get("baseIndoorMapEnabled"));
            if (baseIndoorMapEnabled != null) {
                builder.baseIndoorMapEnabled(baseIndoorMapEnabled);
            }
        }

        if (params.containsKey("showIndoorMapPoi")) {
            Boolean showIndoorMapPoi =
                    FlutterDataConveter.toBoolean(params.get("showIndoorMapPoi"));
            if (showIndoorMapPoi != null) {
                builder.showIndoorMapPoi(showIndoorMapPoi);
            }
        }

        if (params.containsKey("compassPosition")) {

            Map<String, Object>  compassPosition =
                    (Map<String, Object>) FlutterDataConveter.toMap(params.get("compassPosition"));
            Point compassPos = FlutterDataConveter.mapToPoint(compassPosition);
            if (null != compassPos) {
                builder.compassPosition(compassPos);
            }
        }

        if (params.containsKey("mapScaleBarPosition")) {
            final Point mapScaleBarPosition = FlutterDataConveter.toGraphicsPoint(params.get(
                    "mapScaleBarPosition"));
            if (mapScaleBarPosition != null) {
                builder.scaleControlPosition(mapScaleBarPosition);
            }
        }

        if (params.containsKey("mapZoomControlPosition")) {
            final Point mapZoomControlPosition = FlutterDataConveter.toGraphicsPoint(params.get(
                    "mapZoomControlPosition"));
            if (mapZoomControlPosition != null) {
                builder.mapZoomControlPosition(mapZoomControlPosition);
            }
        }

        if (params.containsKey("mapType")) {
            final Object mapType = params.get("mapType");
            if (mapType != null) {
                builder.mapType(FlutterDataConveter.toInt(mapType));
            }
        }

        if (params.containsKey("mapPadding")) {
            final Object mapPadding = params.get("mapPadding");
            if (null != mapPadding) {
                Map<?, ?> padding = FlutterDataConveter.toMap(mapPadding);
                Object top = padding.get("top");
                Object left = padding.get("left");
                Object bottom = padding.get("bottom");
                Object right = padding.get("right");
                if (top != null && left != null && bottom != null && right != null) {
                    builder.viewPadding(FlutterDataConveter.toInt(left),
                            FlutterDataConveter.toInt(top), FlutterDataConveter.toInt(right),
                            FlutterDataConveter.toInt(bottom));
                }
            }
        }

        if (params.containsKey("limitMapBounds")) {
            final Object limitMapBounds = params.get("limitMapBounds");
            if (null != limitMapBounds) {
                LatLngBounds latLngBounds = FlutterDataConveter.toLatLngBounds(limitMapBounds);
                builder.limitMapBounds(latLngBounds);
            }
        }

        return builder.build(viewId, context, mMessenger,
                Constants.ViewType.sMapView, baiduMapOptions);
    }
}
