package com.lamboratory.android.bottleTop.race;

import java.util.ArrayList;
import java.util.List;

import android.graphics.PointF;
import android.util.Pair;

public class GameTrackFactory {

	public static List<Pair<PointF,PointF>> getTrackData(int trackId) {

		List<Pair<PointF,PointF>> track = new ArrayList<Pair<PointF,PointF>>();
		
		switch(trackId) {
		case 0:
			track.add(new Pair<PointF, PointF>(new PointF(900, 410), new PointF(1030, 410)));
			
			track.add(new Pair<PointF, PointF>(new PointF(900, 360), new PointF(1030, 360)));

			track.add(new Pair<PointF, PointF>(new PointF(900, 310), new PointF(1020, 200)));
			track.add(new Pair<PointF, PointF>(new PointF(880, 280), new PointF(1000, 170)));
			track.add(new Pair<PointF, PointF>(new PointF(855, 265), new PointF(960, 150)));
			
			track.add(new Pair<PointF, PointF>(new PointF(730, 265), new PointF(730, 135)));
			track.add(new Pair<PointF, PointF>(new PointF(560, 265), new PointF(560, 135)));

			track.add(new Pair<PointF, PointF>(new PointF(430, 265), new PointF(330, 150)));
			track.add(new Pair<PointF, PointF>(new PointF(410, 280), new PointF(295, 170)));
			track.add(new Pair<PointF, PointF>(new PointF(395, 310), new PointF(275, 200)));

			track.add(new Pair<PointF, PointF>(new PointF(395, 360), new PointF(265, 360)));
			track.add(new Pair<PointF, PointF>(new PointF(395, 450), new PointF(265, 450)));

			track.add(new Pair<PointF, PointF>(new PointF(395, 510), new PointF(275, 590)));
			track.add(new Pair<PointF, PointF>(new PointF(410, 530), new PointF(295, 630)));
			track.add(new Pair<PointF, PointF>(new PointF(430, 540), new PointF(330, 650)));

			track.add(new Pair<PointF, PointF>(new PointF(560, 540), new PointF(560, 665)));
			track.add(new Pair<PointF, PointF>(new PointF(730, 540), new PointF(730, 665)));
			
			//track.add(new Pair<PointF, PointF>(new PointF(650, 540), new PointF(650, 650)));
			//track.add(new Pair<PointF, PointF>(new PointF(650, 540), new PointF(650, 650)));

			track.add(new Pair<PointF, PointF>(new PointF(855, 540), new PointF(960, 650)));
			track.add(new Pair<PointF, PointF>(new PointF(880, 530), new PointF(1000, 630)));
			track.add(new Pair<PointF, PointF>(new PointF(900, 510), new PointF(1020, 590)));

			track.add(new Pair<PointF, PointF>(new PointF(900, 450), new PointF(1030, 450)));

			track.add(new Pair<PointF, PointF>(new PointF(900, 410), new PointF(1030, 410)));
			break;
		
		case 1:
			track.add(new Pair<PointF, PointF>(new PointF(1158, 1806), new PointF(1297, 1806)));
			
			track.add(new Pair<PointF, PointF>(new PointF(1158, 1383), new PointF(1304, 1440)));
			track.add(new Pair<PointF, PointF>(new PointF(1178, 1317), new PointF(1309, 1432)));
			track.add(new Pair<PointF, PointF>(new PointF(1271, 1259), new PointF(1316, 1429)));
			track.add(new Pair<PointF, PointF>(new PointF(1342, 1279), new PointF(1326, 1429)));
			track.add(new Pair<PointF, PointF>(new PointF(1372, 1301), new PointF(1369, 1443)));
			
			track.add(new Pair<PointF, PointF>(new PointF(1418, 1320), new PointF(1421, 1454)));
			track.add(new Pair<PointF, PointF>(new PointF(1445, 1315), new PointF(1474, 1453)));
			track.add(new Pair<PointF, PointF>(new PointF(1469, 1296), new PointF(1526, 1422)));
			
			track.add(new Pair<PointF, PointF>(new PointF(1492, 1265), new PointF(1600, 1314)));
			
			track.add(new Pair<PointF, PointF>(new PointF(1504, 1228), new PointF(1622, 1170)));
			track.add(new Pair<PointF, PointF>(new PointF(1498, 1214), new PointF(1596, 1133)));
			track.add(new Pair<PointF, PointF>(new PointF(1488, 1210), new PointF(1516, 1114)));
			track.add(new Pair<PointF, PointF>(new PointF(1473, 1212), new PointF(1461, 1131)));
			
			track.add(new Pair<PointF, PointF>(new PointF(1446, 1231), new PointF(1429, 1132)));
			track.add(new Pair<PointF, PointF>(new PointF(1390, 1234), new PointF(1406, 1129)));
			track.add(new Pair<PointF, PointF>(new PointF(1319, 1207), new PointF(1381, 1101)));
			
			track.add(new Pair<PointF, PointF>(new PointF(1181, 984), new PointF(1248, 885)));
			track.add(new Pair<PointF, PointF>(new PointF(1162, 974), new PointF(1212, 863)));
			track.add(new Pair<PointF, PointF>(new PointF(1140, 963), new PointF(1121, 839)));
			
			track.add(new Pair<PointF, PointF>(new PointF(1026, 983), new PointF(1061, 845)));
			track.add(new Pair<PointF, PointF>(new PointF(932, 984), new PointF(1035, 844)));
			track.add(new Pair<PointF, PointF>(new PointF(886, 935), new PointF(1019, 834)));
			track.add(new Pair<PointF, PointF>(new PointF(855, 827), new PointF(1001, 785)));
			track.add(new Pair<PointF, PointF>(new PointF(855, 738), new PointF(996, 784)));
			
			track.add(new Pair<PointF, PointF>(new PointF(889, 583), new PointF(1034, 642)));
			
			track.add(new Pair<PointF, PointF>(new PointF(896, 270), new PointF(1034, 325)));
			track.add(new Pair<PointF, PointF>(new PointF(916, 200), new PointF(1040, 314)));
			track.add(new Pair<PointF, PointF>(new PointF(965, 170), new PointF(1046, 309)));
			track.add(new Pair<PointF, PointF>(new PointF(1077, 170), new PointF(1059, 309)));
			track.add(new Pair<PointF, PointF>(new PointF(1165, 193), new PointF(1067, 317)));
			track.add(new Pair<PointF, PointF>(new PointF(1211, 240), new PointF(1071, 325)));
			track.add(new Pair<PointF, PointF>(new PointF(1227, 302), new PointF(1072, 328)));
			
			track.add(new Pair<PointF, PointF>(new PointF(1235, 516), new PointF(1073, 626)));
			track.add(new Pair<PointF, PointF>(new PointF(1247, 557), new PointF(1098, 670)));
			track.add(new Pair<PointF, PointF>(new PointF(1278, 592), new PointF(1143, 696)));
			track.add(new Pair<PointF, PointF>(new PointF(1304, 627), new PointF(1176, 714)));
			
			track.add(new Pair<PointF, PointF>(new PointF(1358, 720), new PointF(1242, 779)));
			
			track.add(new Pair<PointF, PointF>(new PointF(1466, 889), new PointF(1379, 998)));
			track.add(new Pair<PointF, PointF>(new PointF(1550, 913), new PointF(1467, 1029)));
			
			track.add(new Pair<PointF, PointF>(new PointF(1669, 913), new PointF(1593, 1034)));
			track.add(new Pair<PointF, PointF>(new PointF(1749, 933), new PointF(1639, 1050)));
			track.add(new Pair<PointF, PointF>(new PointF(1820, 998), new PointF(1673, 1073)));
			track.add(new Pair<PointF, PointF>(new PointF(1841, 1071), new PointF(1682, 1100)));
			
			track.add(new Pair<PointF, PointF>(new PointF(1841, 1289), new PointF(1683, 1301)));
			
			track.add(new Pair<PointF, PointF>(new PointF(1553, 1785), new PointF(1453, 1713)));
			
			track.add(new Pair<PointF, PointF>(new PointF(1552, 1910), new PointF(1407, 1862)));
			track.add(new Pair<PointF, PointF>(new PointF(1478, 2039), new PointF(1390, 1888)));
			track.add(new Pair<PointF, PointF>(new PointF(1423, 2094), new PointF(1376, 1894)));
			track.add(new Pair<PointF, PointF>(new PointF(1379, 2105), new PointF(1350, 1894)));
			track.add(new Pair<PointF, PointF>(new PointF(1232, 2105), new PointF(1334, 1889)));
			track.add(new Pair<PointF, PointF>(new PointF(1194, 2094), new PointF(1318, 1875)));
			track.add(new Pair<PointF, PointF>(new PointF(1162, 2032), new PointF(1308, 1842)));
			
			track.add(new Pair<PointF, PointF>(new PointF(1158, 1806), new PointF(1297, 1806)));
			break;

		default:
			// Test track
			track.add(new Pair<PointF, PointF>(new PointF(0, 30), new PointF(0, -30)));
			track.add(new Pair<PointF, PointF>(new PointF(60, 30), new PointF(60, -30)));
			track.add(new Pair<PointF, PointF>(new PointF(120, 90), new PointF(120, 30)));
			track.add(new Pair<PointF, PointF>(new PointF(180, 90), new PointF(180, 30)));
			track.add(new Pair<PointF, PointF>(new PointF(240, 30), new PointF(240, -30)));
		}
		return track;
	}

	public static PointF getStartPosition(int trackId) {
		switch(trackId) {
		case 0:
			return new PointF(960, 455);

		case 1:
			return new PointF(1230, 1845);
		default:
			return new PointF(0, 0);
		}
	}
}
