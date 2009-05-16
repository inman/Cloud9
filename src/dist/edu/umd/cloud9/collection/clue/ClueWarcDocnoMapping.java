package edu.umd.cloud9.collection.clue;

import java.io.IOException;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import edu.umd.cloud9.collection.DocnoMapping;
import edu.umd.cloud9.util.HMapKI;

public class ClueWarcDocnoMapping implements DocnoMapping {

	private static final int[] sOffets = { 1, 35583, 63996, 100049, 136309, 171095, 204110, 238916,
			270396, 297824, 330175, 370785, 408143, 439952, 473587, 510569, 546330, 584193, 617681,
			651595, 687998, 725235, 760621, 793422, 827783, 861188, 897280, 922410, 956279, 986687,
			1018873, 1051093, 1087833, 1120132, 1152706, 1190488, 1222929, 1254448, 1287806,
			1322135, 1356109, 1387193, 1420654, 1456212, 1492175, 1529022, 1560541, 1596635,
			1635517, 1669552, 1701407, 1727695, 1764128, 1796152, 1831160, 1868583, 1905354,
			1940813, 1969231, 2000841, 2036445, 2068801, 2103121, 2138170, 2166439, 2198118,
			2230017, 2260728, 2283035, 2315139, 2351379, 2386387, 2420582, 2453821, 2487881,
			2517985, 2555901, 2587689, 2622637, 2657003, 2697774, 2733443, 2765964, 2799821,
			2830400, 2862001, 2895167, 2927567, 2963100, 2998902, 3037318, 3070362, 3104282,
			3140935, 3180345, 3214865, 3245853, 3280892, 3315728, 3349360, 3382357, 3416661,
			3450900, 3485981, 3519343, 3549852, 3580555, 3615589, 3648212, 3678646, 3716906,
			3750258, 3790974, 3825467, 3860392, 3894197, 3923959, 3957728, 3994725, 4032613,
			4066808, 4092960, 4129994, 4161269, 4196978, 4231268, 4267306, 4299506, 4329826,
			4361737, 4396634, 4432768, 4466447, 4500398, 4531994, 4564337, 4598248, 4629577,
			4665980, 4700037, 4733821, 4767410, 4801280, 4834261, 4867604, 4918079, 4954458,
			4991149, 5025307, 5057142, 5083458, 5111400, 5145321, 5179878, 5215327, 5248867,
			5283939, 5311604, 5344235, 5376009, 5407200, 5437371, 5471428, 5503561, 5539279,
			5569387, 5603840, 5637736, 5673916, 5707959, 5741541, 5778612, 5811314, 5846335,
			5880406, 5919152, 5951744, 5971719, 6010121, 6038130, 6074953, 6108961, 6143468,
			6176057, 6203880, 6240421, 6274884, 6307636, 6341542, 6375656, 6413078, 6447557,
			6483421, 6521186, 6558846, 6588411, 6627353, 6661309, 6695577, 6727950, 6756756,
			6791754, 6831980, 6864975, 6900537, 6937486, 6978639, 7010059, 7043561, 7078127,
			7111405, 7146596, 7180476, 7212940, 7243987, 7277459, 7312983, 7349486, 7385617,
			7421668, 7461652, 7495511, 7536846, 7577201, 7608240, 7643422, 7675392, 7709369,
			7745285, 7781065, 7817066, 7850995, 7883829, 7915702, 7947023, 7978784, 8013838,
			8047752, 8084778, 8114248, 8144810, 8176906, 8209930, 8237166, 8269106, 8299308,
			8336332, 8370168, 8393113, 8428750, 8463529, 8498731, 8529181, 8560269, 8593923,
			8627955, 8655126, 8689315, 8727813, 8761884, 8795160, 8832182, 8866024, 8899132,
			8931969, 8959921, 8994883, 9027923, 9059388, 9087714, 9120516, 9150067, 9182043,
			9214997, 9248476, 9280739, 9313408, 9355040, 9392742, 9427217, 9459591, 9497314,
			9529082, 9565072, 9600613, 9630983, 9664848, 9694599, 9729825, 9768497, 9801847,
			9817690, 9849985, 9885983, 9920524, 9954766, 9989068, 10028634, 10060340, 10093701,
			10124468, 10157962, 10187429, 10219953, 10258494, 10288585, 10320574, 10349021,
			10381161, 10413447, 10445055, 10479290, 10506912, 10540578, 10575244, 10609917,
			10643534, 10675471, 10711863, 10746714, 10777698, 10814330, 10839682, 10873038,
			10906203, 10937669, 10962517, 11001219, 11032503, 11060195, 11090686, 11125834,
			11157844, 11189071, 11220066, 11251857, 11285574, 11320250, 11350796, 11382694,
			11416281, 11439841, 11474524, 11509123, 11548564, 11582113, 11618910, 11651487,
			11685564, 11718085, 11754141, 11784553, 11809636, 11844748, 11873263, 11911117,
			11943669, 11976719, 12007062, 12040829, 12074994, 12112177, 12139646, 12168573,
			12200893, 12233296, 12259503, 12292766, 12322253, 12355010, 12384653, 12417003,
			12454086, 12491527, 12529345, 12566202, 12602220, 12636227, 12667282, 12694895,
			12728802, 12760152, 12796033, 12829537, 12857232, 12889985, 12921912, 12952293,
			12978680, 13010885, 13038530, 13074828, 13102192, 13132852, 13160510, 13196146,
			13229798, 13259170, 13295776, 13326396, 13361744, 13400756, 13440854, 13474589,
			13515586, 13550128, 13591002, 13614954, 13651949, 13685265, 13729038, 13766530,
			13806382, 13836567, 13871692, 13910910, 13945322, 13985327, 14021906, 14061257,
			14092395, 14127348, 14158169, 14193701, 14230775, 14269319, 14311316, 14342455,
			14380555, 14419850, 14462919, 14498109, 14536922, 14564816, 14606669, 14639021,
			14683200, 14723364, 14752444, 14790567, 14832316, 14876902, 14914293, 14954909,
			14994725, 15043262, 15076394, 15111217, 15147465, 15184968, 15227255, 15269456,
			15309312, 15350488, 15385353, 15424153, 15466082, 15507362, 15547185, 15581739,
			15621649, 15656339, 15687721, 15727309, 15763100, 15802978, 15839675, 15865789,
			15905801, 15948048, 15985785, 16016752, 16049325, 16082124, 16119130, 16161287,
			16200361, 16235050, 16273706, 16296716, 16334130, 16375998, 16415073, 16455941,
			16479515, 16517030, 16557459, 16585438, 16617161, 16649995, 16684045, 16720666,
			16762557, 16801272, 16843990, 16877052, 16908832, 16951867, 16992039, 17017585,
			17049138, 17088401, 17132856, 17175151, 17210648, 17245123, 17286155, 17325588,
			17369263, 17411163, 17443413, 17477389, 17519585, 17557622, 17597074, 17640731,
			17673985, 17711087, 17754214, 17796490, 17839616, 17880631, 17924350, 17965688,
			17990123, 18030064, 18067111, 18105509, 18145698, 18183093, 18224802, 18261548,
			18300992, 18340307, 18375785, 18414630, 18457421, 18504663, 18546678, 18580140,
			18617439, 18656297, 18695940, 18736393, 18773628, 18810251, 18851378, 18893536,
			18927924, 18962430, 19004030, 19033957, 19074363, 19114984, 19154795, 19189730,
			19227563, 19264339, 19310480, 19351570, 19389324, 19428916, 19465383, 19510555,
			19550873, 19586155, 19630220, 19671310, 19715016, 19757345, 19795669, 19828035,
			19870087, 19908747, 19949205, 19993399, 20035927, 20072098, 20114228, 20156058,
			20191572, 20227356, 20270236, 20312317, 20352444, 20390023, 20427470, 20469320,
			20511108, 20538769, 20571471, 20608977, 20646523, 20690757, 20732064, 20762873,
			20801636, 20841541, 20874277, 20914808, 20960289, 20999734, 21038488, 21080808,
			21115112, 21161817, 21203034, 21246788, 21288979, 21327249, 21369006, 21404424,
			21437728, 21475575, 21515451, 21556617, 21592868, 21632584, 21673618, 21710277,
			21746968, 21788454, 21832968, 21870504, 21910511, 21950479, 21992634, 22042566,
			22080838, 22121396, 22153161, 22196945, 22234020, 22267494, 22307688, 22343693,
			22381333, 22420133, 22458117, 22497757, 22536073, 22567614, 22602882, 22639233,
			22675624, 22713659, 22756672, 22794002, 22829355, 22870150, 22896871, 22927736,
			22964681, 23008603, 23045157, 23083345, 23117220, 23154248, 23191607, 23233466,
			23272274, 23315570, 23356751, 23396608, 23439674, 23468664, 23502895, 23547931,
			23583041, 23623732, 23665459, 23706064, 23734873, 23778723, 23817901, 23851877,
			23886767, 23914152, 23959912, 23994059, 24031435, 24064574, 24103006, 24135771,
			24175611, 24216068, 24253564, 24291868, 24330749, 24379918, 24412141, 24448013,
			24488863, 24529184, 24565789, 24603740, 24645679, 24683405, 24717342, 24759686,
			24798064, 24840929, 24882773, 24919770, 24960241, 25000319, 25036552, 25076814,
			25115347, 25153488, 25197437, 25237420, 25284247, 25322455, 25357909, 25393526,
			25436102, 25475622, 25515314, 25555283, 25595164, 25632261, 25674861, 25714713,
			25751982, 25791014, 25826594, 25865858, 25902233, 25943511, 25979188, 26014890,
			26047034, 26087554, 26125219, 26169390, 26205947, 26247732, 26288528, 26330671,
			26357691, 26399632, 26424058, 26466577, 26504688, 26542809, 26580095, 26612868,
			26654050, 26689856, 26730356, 26770051, 26803040, 26844305, 26884795, 26932287,
			26975780, 27015391, 27057658, 27083577, 27125502, 27162758, 27202581, 27245337,
			27281315, 27319688, 27364810, 27410442, 27448869, 27486808, 27528553, 27563542,
			27600842, 27635050, 27674500, 27713784, 27751668, 27786728, 27826022, 27866576,
			27907009, 27946651, 27987601, 28030890, 28058558, 28101521, 28140791, 28180082,
			28216393, 28255868, 28295602, 28335180, 28378419, 28417769, 28459112, 28506810,
			28534017, 28572480, 28614048, 28653699, 28694431, 28734055, 28777729, 28820819,
			28867170, 28907919, 28949398, 28992813, 29034594, 29070368, 29109618, 29151051,
			29190737, 29231972, 29270833, 29308486, 29342772, 29384965, 29422747, 29463552,
			29504923, 29541356, 29581189, 29618019, 29653462, 29691133, 29717884, 29759273,
			29798348, 29835809, 29879788, 29922088, 29964910, 30013572, 30053195, 30083930,
			30132642, 30171964, 30212773, 30256904, 30295511, 30333723, 30373104, 30408844,
			30447834, 30488437, 30531513, 30571744, 30598717, 30645437, 30689388, 30728282,
			30767403, 30810366, 30842932, 30881871, 30914034, 30956923, 31000769, 31039512,
			31070834, 31111121, 31149852, 31191241, 31234905, 31271386, 31315885, 31354858,
			31396405, 31434708, 31471201, 31513691, 31554118, 31595008, 31633049, 31664879,
			31705288, 31744357, 31777400, 31821485, 31862377, 31903768, 31943746, 31980375,
			32020691, 32058307, 32096652, 32132036, 32172753, 32206477, 32242893, 32284874,
			32328651, 32366835, 32394293, 32431793, 32475698, 32515945, 32551020, 32592353,
			32632799, 32671781, 32719383, 32760507, 32799386, 32835141, 32876195, 32914526,
			32953584, 32993021, 33027106, 33070506, 33110475, 33151232, 33194376, 33227778,
			33266976, 33305912, 33340329, 33382282, 33421027, 33458734, 33499146, 33536385,
			33578597, 33618956, 33661128, 33694533, 33734509, 33767790, 33810509, 33853311,
			33892456, 33929323, 33967770, 34000384, 34034701, 34075600, 34116660, 34153492,
			34188886, 34230799, 34268252, 34304054, 34343147, 34385089, 34423779, 34461149,
			34499415, 34538455, 34583916, 34627354, 34669404, 34708488, 34743465, 34783128,
			34821505, 34858440, 34903932, 34945791, 34983971, 35026100, 35065481, 35104265,
			35138173, 35182588, 35222932, 35251320, 35292548, 35325165, 35365592, 35400030,
			35441250, 35481715, 35524473, 35562488, 35603176, 35646114, 35689977, 35727748,
			35767416, 35798503, 35840486, 35883082, 35919433, 35964578, 36010820, 36054049,
			36094546, 36136922, 36172119, 36207466, 36242039, 36285868, 36324690, 36365601,
			36405515, 36439568, 36476303, 36517086, 36559058, 36590536, 36631831, 36672875,
			36712208, 36749381, 36794998, 36833780, 36873858, 36911475, 36953072, 36992324,
			37029287, 37070909, 37109254, 37142084, 37182722, 37226975, 37270587, 37305777,
			37346014, 37385547, 37427444, 37472046, 37501770, 37541531, 37578700, 37622486,
			37661511, 37701992, 37748109, 37778303, 37813484, 37854564, 37894960, 37938868,
			37979930, 38011413, 38052684, 38089415, 38128352, 38168447, 38209323, 38247498,
			38287168, 38325409, 38366307, 38405537, 38445779, 38480565, 38521154, 38559400,
			38595836, 38635356, 38675292, 38714451, 38757418, 38797747, 38839780, 38884151,
			38924289, 38961151, 39003544, 39045280, 39088699, 39127648, 39165352, 39202123,
			39239126, 39278121, 39321125, 39361327, 39398239, 39428984, 39470526, 39509581,
			39547359, 39588860, 39631143, 39672962, 39711693, 39752478, 39788568, 39826088,
			39862906, 39902937, 39946155, 39983261, 40012924, 40054787, 40094656, 40130941,
			40172894, 40212430, 40247671, 40285951, 40326190, 40365146, 40405276, 40446102,
			40487945, 40528865, 40568644, 40595979, 40634386, 40665306, 40705659, 40749928,
			40784208, 40807807, 40850057, 40888355, 40925959, 40970183, 41012681, 41055587,
			41093564, 41133937, 41177312, 41219625, 41259498, 41296903, 41335695, 41369615,
			41411808, 41448602, 41486212, 41529011, 41567318, 41597871, 41639105, 41680418,
			41719962, 41756384, 41792349, 41828244, 41864317, 41906200, 41948682, 41988550,
			42025180, 42050696, 42083540, 42123665, 42156374, 42194963, 42231418, 42270980,
			42302618, 42342530, 42381255, 42418966, 42455335, 42476824, 42518215, 42564200,
			42604544, 42649222, 42691142, 42730307, 42762076, 42800200, 42847123, 42881029,
			42921324, 42964491, 43004142, 43044465, 43083299, 43123397, 43155734, 43187073,
			43229131, 43265488, 43303235, 43340341, 43380251, 43418153, 43457414, 43493762,
			43535364, 43575886, 43619170, 43656585, 43699907, 43738638, 43770860, 43787011,
			43819894, 43858014, 43889589, 43927973, 43967583, 44004203, 44040730, 44069946,
			44107870, 44148138, 44187603, 44225495, 44262895, 44284470, 44306329, 44328083,
			44350078, 44371931, 44393899, 44415460, 44437390, 44459368, 44481077, 44502565,
			44524189, 44545874, 44567250, 44588863, 44610759, 44632834, 44654777, 44676781,
			44698580, 44722226, 44746164, 44769886, 44793964, 44817741, 44841699, 44865335,
			44889161, 44913114, 44936961, 44960590, 44984302, 45008095, 45032311, 45056516,
			45080505, 45104106, 45128094, 45152300, 45175854, 45201346, 45226276, 45251516,
			45276792, 45302499, 45327835, 45353059, 45378445, 45403933, 45429183, 45454711,
			45480200, 45505168, 45530290, 45555389, 45580687, 45606210, 45631509, 45656623,
			45682222, 45705872, 45729949, 45753572, 45777423, 45791731, 45815446, 45839284,
			45863030, 45886877, 45910663, 45934606, 45958409, 45982191, 46005970, 46029723,
			46053585, 46077236, 46101161, 46124919, 46148707, 46172513, 46193889, 46217529,
			46241219, 46265072, 46288668, 46312422, 46336151, 46359855, 46383759, 46407770,
			46431495, 46455392, 46479099, 46502760, 46526939, 46550806, 46574780, 46598657,
			46624193, 46649659, 46673617, 46698788, 46724142, 46749512, 46774924, 46800332,
			46825651, 46850885, 46876385, 46901950, 46927298, 46951165, 46976715, 47002295,
			47027408, 47052613, 47073329, 47093968, 47119487, 47145194, 47170602, 47191160,
			47212112, 47232555, 47253234, 47273759, 47294239, 47314797, 47335341, 47355893,
			47376696, 47397453, 47417878, 47441223, 47464633, 47485290, 47505992, 47529893,
			47553429, 47578604, 47599359, 47619869, 47643358, 47666374, 47672871, 47695911,
			47719271, 47742823, 47766298, 47789797, 47812846, 47836448, 47859863, 47883182,
			47906520, 47930322, 47953964, 47975968, 47999340, 48023050, 48045434, 48068991,
			48092772, 48116316, 48136898, 48160700, 48184421, 48207189, 48230891, 48250835,
			48273534, 48294235, 48317628, 48343180, 48366655, 48386922, 48409714, 48433350,
			48454190, 48478212, 48501533, 48521939, 48544344, 48567682, 48587482, 48590648,
			48614289, 48616682, 48636394, 48656868, 48676542, 48697345, 48718077, 48721309,
			48741720, 48762204, 48782841, 48785051, 48810275, 48829804, 48850289, 48854325,
			48875134, 48895585, 48900895, 48924371, 48928729, 48930735, 48936068, 48938347,
			48941498, 48944787, 48968475, 48992313, 48994862, 48999597, 49002148, 49003821,
			49006422, 49030004, 49053758, 49073856, 49094194, 49119606, 49122508, 49128075,
			49148754, 49171862, 49195528, 49215680, 49218729, 49243100, 49267706, 49292213,
			49317127, 49341529, 49366121, 49390513, 49414928, 49439233, 49463532, 49487781,
			49512184, 49536735, 49561111, 49585508, 49610135, 49634386, 49658835, 49683305,
			49707715, 49730847, 49754362, 49777624, 49800884, 49824244, 49847688, 49871470,
			49894632, 49917910, 49941228, 49964432, 49987664, 50010961, 50034275, 50057484,
			50080841, 50104119, 50127588, 50151053, 50174315, 50176891, 50179163, 50181572,
			50183985, 50186147, 50188910, 50191076, 50192507, 50195208, 50197373, 50199562,
			50202007, 50204533, 50207052, 50209027, 50212046, 50214192, 50216471, 50218602 };

	private static final HMapKI<String> sSubDirMapping = new HMapKI<String>();

	static {
		sSubDirMapping.put("en0000", 0);
		sSubDirMapping.put("en0001", 100);
		sSubDirMapping.put("en0002", 200);
		sSubDirMapping.put("en0003", 300);
		sSubDirMapping.put("en0004", 400);
		sSubDirMapping.put("en0005", 500);
		sSubDirMapping.put("en0006", 600);
		sSubDirMapping.put("en0007", 700);
		sSubDirMapping.put("en0008", 800);
		sSubDirMapping.put("en0009", 900);
		sSubDirMapping.put("en0010", 1000);
		sSubDirMapping.put("en0011", 1100);
		sSubDirMapping.put("enwp00", 1200);
		sSubDirMapping.put("enwp01", 1300);
		sSubDirMapping.put("enwp02", 1400);
		sSubDirMapping.put("enwp03", 1432);
	}

	public int getDocno(String docid) {
		//"clueweb09-en0007-91-00000"
		
		if ( docid == null)
			return -1;
		
		String sec = docid.substring(10, 16);
		int secStart = sSubDirMapping.get(sec);
		
		int file = Integer.parseInt(docid.substring(17, 19));
		int cnt = Integer.parseInt(docid.substring(20, 25));
		
		int idx = secStart + file;
		//System.out.println(sec + " " + file + " " + idx + " " + cnt);
		
		int docno = sOffets[idx] + cnt;
		//System.out.println("docno=" + docno);

		return docno;
	}

	public String getDocid(int docno) {
		throw new UnsupportedOperationException();
	}

	public void loadMapping(Path p, FileSystem fs) throws IOException {
	}

}
