/*
 * Copyright 2013 The Android Open Source Project
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

package com.app.pescaderiaespe;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import clases.Compra;
import clases.DetalleCompras;
import clases.proveedor;
import clases.genero;
import bd.MySQLiteHelper;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import android.widget.ListView;


public class MainActivity extends Activity {
	
	
	public static MainActivity main;
	
	//Drawer
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    
    private CharSequence mTitle;
    private String[] mOptions;
    
    Integer[] imageId = {
    	      R.drawable.ic_basket,
    	      R.drawable.ic_eye ,
    	      R.drawable.ic_action_download,
    	      R.drawable.ic_action_person,
    	      R.drawable.ic_fish,
    	      R.drawable.ic_action_settings
    	      
    	  };
    
    //Objetos en pantalla    
    private Button btSig;
    private Button btGuardar;
    
    private Spinner spProv;
    private Spinner spGenero;
    
    private EditText cantidad;
    private EditText precio;
    private EditText fecha;
    
    private Compra miCompra;
    private ArrayList<DetalleCompras> Compras;
    
    private String proveedor;
    
    public void proveedores()
    {
      	 MySQLiteHelper db = new MySQLiteHelper(this);
    	 proveedor aux=new proveedor();
    	 aux.setId(99); aux.setNombre("SONIA"); db.addProveedor(aux);
    	 aux.setId(100); aux.setNombre("PONCE"); db.addProveedor(aux);
    	 aux.setId(101); aux.setNombre("ENDUMAR, S.L."); db.addProveedor(aux);
    	 aux.setId(102); aux.setNombre("JOAQUIN"); db.addProveedor(aux);
    	 aux.setId(103); aux.setNombre("ANÓNIMO PESCADOR"); db.addProveedor(aux);
    	 aux.setId(104); aux.setNombre("NERE EGAÑA"); db.addProveedor(aux);
    	 aux.setId(105); aux.setNombre("IRAETA"); db.addProveedor(aux);
    	 aux.setId(106); aux.setNombre("PROAFER"); db.addProveedor(aux);
    	 aux.setId(107); aux.setNombre("DONOSTI COFRADÍA"); db.addProveedor(aux);
    	 aux.setId(108); aux.setNombre("ANGULAS AGUINAGA"); db.addProveedor(aux);
    	 aux.setId(109); aux.setNombre("ANGULAS MAYOZ"); db.addProveedor(aux);
    	 aux.setId(110); aux.setNombre("CAROL- PESCADERÍA ESPE"); db.addProveedor(aux);
    	 aux.setId(111); aux.setNombre("OLIVERI PESCADOS"); db.addProveedor(aux);
    	 aux.setId(112); aux.setNombre("GABARDERAL"); db.addProveedor(aux);
    	 aux.setId(14); aux.setNombre("BERECIARTUA PESCADOS"); db.addProveedor(aux);
    	 aux.setId(15); aux.setNombre("CELESTINO DE LA CRUZ"); db.addProveedor(aux);
    	 aux.setId(16); aux.setNombre("RODOLFO,PESCADOS Y MARISCOS S.A."); db.addProveedor(aux);
    	 aux.setId(17); aux.setNombre("PESCASAEZ S.A."); db.addProveedor(aux);
    	 aux.setId(18); aux.setNombre("COFRADIA DE PESCADORES SAN PEDRO"); db.addProveedor(aux);
    	 aux.setId(19); aux.setNombre("BORJA, PESCADOS Y MARISCOS"); db.addProveedor(aux);
    	 aux.setId(20); aux.setNombre("ARRAINETXE, PESCADOS Y MARISCOS S.L."); db.addProveedor(aux);
    	 aux.setId(21); aux.setNombre("AYALA, PESCADOS S.L.L."); db.addProveedor(aux);
    	 aux.setId(22); aux.setNombre("ARKUPE, PESCADOS Y MARISCOS S.A."); db.addProveedor(aux);
    	 aux.setId(23); aux.setNombre("TUDELA INTERNACIONAL FISH S.L., TUINFISH, S.L."); db.addProveedor(aux);
    	 aux.setId(24); aux.setNombre("I"); db.addProveedor(aux);
    	 aux.setId(25); aux.setNombre("MENDIZABAL ,S.A. PESCADOS"); db.addProveedor(aux);
    	 aux.setId(26); aux.setNombre("HERMANOS TAMARGO,S.L"); db.addProveedor(aux);
    	 aux.setId(27); aux.setNombre("TAMARGO PESCADOS .S.A."); db.addProveedor(aux);
    	 aux.setId(28); aux.setNombre("CASTELLANOS PESCADOS ,S.A."); db.addProveedor(aux);
    	 aux.setId(29); aux.setNombre("OLABERRIA PESQUERA S.L."); db.addProveedor(aux);
    	 aux.setId(30); aux.setNombre("ANTOÑANA PESCADOS  S.L."); db.addProveedor(aux);
    	 aux.setId(31); aux.setNombre("SANCHEZ MARTICORENA, IÑIGO"); db.addProveedor(aux);
    	 aux.setId(32); aux.setNombre("ARGIMAR PESCADOS ,  S.L"); db.addProveedor(aux);
    	 aux.setId(33); aux.setNombre("KERMAN PESCADOS Y MARISCOS"); db.addProveedor(aux);
    	 aux.setId(34); aux.setNombre("MIGUEZ   PESCADOS  ,S.L"); db.addProveedor(aux);
    	 aux.setId(35); aux.setNombre("LAZGUE , S.L"); db.addProveedor(aux);
    	 aux.setId(36); aux.setNombre("JOSE LUIS SOTELO"); db.addProveedor(aux);
    	 aux.setId(37); aux.setNombre("VIVEROS SAN PEDRO"); db.addProveedor(aux);
    	 aux.setId(38); aux.setNombre("IZAGUIRRE JERONIMO"); db.addProveedor(aux);
    	 aux.setId(39); aux.setNombre("TXEMAR , S.A."); db.addProveedor(aux);
    	 aux.setId(40); aux.setNombre("BIKOTE PESQUERA , S.A.L."); db.addProveedor(aux);
    	 aux.setId(41); aux.setNombre("J. OCHOA .PESCADOS"); db.addProveedor(aux);
    	 aux.setId(42); aux.setNombre("RICARDO  BARCENA, PESCADOS S.L."); db.addProveedor(aux);
    	 aux.setId(43); aux.setNombre("ITSAS ZABAL .FERMIN LARRAÑAGA"); db.addProveedor(aux);
    	 aux.setId(44); aux.setNombre("BEITU  2000 ,S.L"); db.addProveedor(aux);
    	 aux.setId(45); aux.setNombre("ATXEGA  PESCADOS Y MARISCOS"); db.addProveedor(aux);
    	 aux.setId(46); aux.setNombre("ALTUPESCA  EDUARDO ALTUNA"); db.addProveedor(aux);
    	 aux.setId(47); aux.setNombre("NANCLARES  PESCADOS  S.L."); db.addProveedor(aux);
    	 aux.setId(48); aux.setNombre("FISHING  COMPANY  S.L."); db.addProveedor(aux);
    	 aux.setId(49); aux.setNombre("SARASOLA, IÑIGO  S. A"); db.addProveedor(aux);
    	 aux.setId(50); aux.setNombre("FAST  FISH E  S.L."); db.addProveedor(aux);
    	 aux.setId(51); aux.setNombre("CASA    SOFIA   , S.L."); db.addProveedor(aux);
    	 aux.setId(52); aux.setNombre("THALASSA   .S.L"); db.addProveedor(aux);
    	 aux.setId(53); aux.setNombre("BERMIPESCA   S.L."); db.addProveedor(aux);
    	 aux.setId(54); aux.setNombre("IRUNE  PESCADOS  Y  MARISCOS"); db.addProveedor(aux);
    	 aux.setId(55); aux.setNombre("CARLOS IGLESIAS DE LOS MODOS"); db.addProveedor(aux);
    	 aux.setId(56); aux.setNombre("JULIAN  PESCADOS  S.L."); db.addProveedor(aux);
    	 aux.setId(57); aux.setNombre("G.J.A. PESCADOS"); db.addProveedor(aux);
    	 aux.setId(58); aux.setNombre("PALACIOS, ESTEBAN  S.L."); db.addProveedor(aux);
    	 aux.setId(59); aux.setNombre("ARRAIN PESCADOS Y MARISCOS"); db.addProveedor(aux);
    	 aux.setId(60); aux.setNombre("RIVERA  PESCADOS"); db.addProveedor(aux);
    	 aux.setId(62); aux.setNombre("BIAMAR PESCADOS"); db.addProveedor(aux);
    	 aux.setId(63); aux.setNombre("EUSTAQUIO SAGARZAZU"); db.addProveedor(aux);
    	 aux.setId(64); aux.setNombre("ORTIGALA PESCADOS"); db.addProveedor(aux);
    	 aux.setId(65); aux.setNombre("PEPI"); db.addProveedor(aux);
    	 aux.setId(66); aux.setNombre("OLATU ,SL"); db.addProveedor(aux);
    	 aux.setId(67); aux.setNombre("SANROS .S . L."); db.addProveedor(aux);
    	 aux.setId(68); aux.setNombre("LOPEZ  PESCADOS S.L."); db.addProveedor(aux);
    	 aux.setId(69); aux.setNombre("GINES E HIJOS PESCADOS S.L."); db.addProveedor(aux);
    	 aux.setId(70); aux.setNombre("TELLERIA GARCIA .S.A."); db.addProveedor(aux);
    	 aux.setId(71); aux.setNombre("MANUEL MALVIDO ALVAREZ"); db.addProveedor(aux);
    	 aux.setId(72); aux.setNombre("CARPA PESQUERA, S.A."); db.addProveedor(aux);
    	 aux.setId(73); aux.setNombre("IRE PESCADOS S.L."); db.addProveedor(aux);
    	 aux.setId(74); aux.setNombre("DENA FISH S.L."); db.addProveedor(aux);
    	 aux.setId(75); aux.setNombre("SANZ, MIGUEL  S.L."); db.addProveedor(aux);
    	 aux.setId(76); aux.setNombre("FERNANDO MARQUES"); db.addProveedor(aux);
    	 aux.setId(77); aux.setNombre("MARAURI, MAYER PESCADOS Y MARISCOS"); db.addProveedor(aux);
    	 aux.setId(78); aux.setNombre("INTERFIST"); db.addProveedor(aux);
    	 aux.setId(79); aux.setNombre("NORTEMAR PESCADOS .S.L."); db.addProveedor(aux);
    	 aux.setId(81); aux.setNombre("COMERCIAL IBARREN S.L."); db.addProveedor(aux);
    	 aux.setId(82); aux.setNombre("IRUÑA GESTION 2000 S.L."); db.addProveedor(aux);
    	 aux.setId(83); aux.setNombre("ZALGOI S.L."); db.addProveedor(aux);
    	 aux.setId(84); aux.setNombre("FERGAR PESCADOS"); db.addProveedor(aux);
    	 aux.setId(85); aux.setNombre("KYTAIPON"); db.addProveedor(aux);
    	 aux.setId(86); aux.setNombre("SALVIO PESCADOS S.L."); db.addProveedor(aux);
    	 aux.setId(87); aux.setNombre("0"); db.addProveedor(aux);
    	 aux.setId(88); aux.setNombre("JAMER PESCADOS"); db.addProveedor(aux);
    	 aux.setId(89); aux.setNombre("JOSEMA KALAMENDI"); db.addProveedor(aux);
    	 aux.setId(90); aux.setNombre("AIMAR"); db.addProveedor(aux);
    	 aux.setId(91); aux.setNombre("AIZKALA S.L., KALAMENDI"); db.addProveedor(aux);
    	 aux.setId(92); aux.setNombre("KALAMENDI, SARASOLA  PESCADOS"); db.addProveedor(aux);
    	 aux.setId(93); aux.setNombre("JORGE KALAMENDI"); db.addProveedor(aux);
    	 aux.setId(94); aux.setNombre("KITAYPON"); db.addProveedor(aux);
    	 aux.setId(95); aux.setNombre("CALADERO"); db.addProveedor(aux);
    	 aux.setId(96); aux.setNombre("LAUCASAL"); db.addProveedor(aux);
    	 aux.setId(97); aux.setNombre("AKAMAR"); db.addProveedor(aux);
    	 aux.setId(98); aux.setNombre("COEXPE"); db.addProveedor(aux);

    	
    	
    }
    public void genero()
    {
    	 MySQLiteHelper db = new MySQLiteHelper(this);
    	 genero aux=new genero();
    	
    	 aux.setId(267); aux.setNombre("ANCHOILLA ARRASTRE");  db.addGenero(aux); 
    	 aux.setId(268); aux.setNombre("MEDIANA DE ANZUELO DEL CANTÁBRICO");  db.addGenero(aux); 
    	 aux.setId(269); aux.setNombre("GAMBA CONGELADA");  db.addGenero(aux); 
    	 aux.setId(270); aux.setNombre("ARRAIGORRI DE AQUI");  db.addGenero(aux); 
    	 aux.setId(271); aux.setNombre("GALLO DE AQUI");  db.addGenero(aux); 
    	 aux.setId(272); aux.setNombre("COLAS DE MEDIANA DE ANZUELO");  db.addGenero(aux); 
    	 aux.setId(273); aux.setNombre("ALMEJA GALLEGA");  db.addGenero(aux); 
    	 aux.setId(274); aux.setNombre("ANTXOA DEL CANTABRICO");  db.addGenero(aux); 
    	 aux.setId(275); aux.setNombre("SUMINISTRO DE PESCADO, CUARTO TRIMESTRE 2012");  db.addGenero(aux); 
    	 aux.setId(276); aux.setNombre("SEPIA");  db.addGenero(aux); 
    	 aux.setId(277); aux.setNombre("PALOMETA ROJA");  db.addGenero(aux); 
    	 aux.setId(278); aux.setNombre("DONCELLAS");  db.addGenero(aux); 
    	 aux.setId(279); aux.setNombre("SUMINISTRO DE PESCADO, TERCER TRIMESTRE 2012");  db.addGenero(aux); 
    	 aux.setId(280); aux.setNombre("SERVICIO DE CÁMARAS");  db.addGenero(aux); 
    	 aux.setId(281); aux.setNombre("SONIA");  db.addGenero(aux); 
    	 aux.setId(282); aux.setNombre("SALMONETE TERCIADO");  db.addGenero(aux); 
    	 aux.setId(283); aux.setNombre("SALMONETE ANZUELO");  db.addGenero(aux); 
    	 aux.setId(284); aux.setNombre("CAJA ");  db.addGenero(aux); 
    	 aux.setId(285); aux.setNombre("MEJILLÓN ESCABECHE");  db.addGenero(aux); 
    	 aux.setId(286); aux.setNombre("VOLCADA");  db.addGenero(aux); 
    	 aux.setId(287); aux.setNombre("MEJILLÓN MEDIA CONCHA");  db.addGenero(aux); 
    	 aux.setId(288); aux.setNombre("ERIZO DE MAR");  db.addGenero(aux); 
    	 aux.setId(289); aux.setNombre("AKULA");  db.addGenero(aux); 
    	 aux.setId(290); aux.setNombre("CAJA GALLEGA");  db.addGenero(aux); 
    	 aux.setId(291); aux.setNombre("MERLUZA REUS");  db.addGenero(aux); 
    	 aux.setId(293); aux.setNombre("PEZ ESPADA");  db.addGenero(aux); 
    	 aux.setId(294); aux.setNombre("NESKAZAHARRA");  db.addGenero(aux); 
    	 aux.setId(295); aux.setNombre("LAMPO");  db.addGenero(aux); 
    	 aux.setId(296); aux.setNombre("MEJILLON NATURAL");  db.addGenero(aux); 
    	 aux.setId(297); aux.setNombre("SALMON SALVAJE");  db.addGenero(aux); 
    	 aux.setId(298); aux.setNombre("MAKAELA");  db.addGenero(aux); 
    	 aux.setId(299); aux.setNombre("TXANGURRO COCIDO");  db.addGenero(aux); 
    	 aux.setId(300); aux.setNombre("PINTXO MARISCO");  db.addGenero(aux); 
    	 aux.setId(301); aux.setNombre("KING ARTIK NATURAL");  db.addGenero(aux); 
    	 aux.setId(302); aux.setNombre("KING ARTIC AJILLO");  db.addGenero(aux); 
    	 aux.setId(303); aux.setNombre("SUMINISTRO DE PESCADO, SEGUNDO TRIMESTRE 2013");  db.addGenero(aux); 
    	 aux.setId(304); aux.setNombre("PIMIENTOS GALLEGOS");  db.addGenero(aux); 
    	 aux.setId(305); aux.setNombre("ELABORACIÓN CONGELADO");  db.addGenero(aux); 
    	 aux.setId(306); aux.setNombre("CALAMAR PELADO");  db.addGenero(aux); 
    	 aux.setId(307); aux.setNombre("TOLLA");  db.addGenero(aux); 
    	 aux.setId(308); aux.setNombre("KAYA");  db.addGenero(aux); 
    	 aux.setId(309); aux.setNombre("AKERRA");  db.addGenero(aux); 
    	 aux.setId(310); aux.setNombre("HUESOS DE SAPO");  db.addGenero(aux); 
    	 aux.setId(311); aux.setNombre("URTA");  db.addGenero(aux); 
    	 aux.setId(312); aux.setNombre("CALAMAR CONGELADO INVERSIÓN PROPIA");  db.addGenero(aux); 
    	 aux.setId(313); aux.setNombre("DURDO");  db.addGenero(aux); 
    	 aux.setId(314); aux.setNombre("GALLINETA ROJA");  db.addGenero(aux); 
    	 aux.setId(315); aux.setNombre("MERLUZA DE PINCHO 6P");  db.addGenero(aux); 
    	 aux.setId(316); aux.setNombre("MERLUZA DE PINCHO 7P");  db.addGenero(aux); 
    	 aux.setId(317); aux.setNombre("MERLUZA DE PINCHO 8P");  db.addGenero(aux); 
    	 aux.setId(318); aux.setNombre("PEZ LIMÓN");  db.addGenero(aux); 
    	 aux.setId(319); aux.setNombre("REUS");  db.addGenero(aux); 
    	 aux.setId(320); aux.setNombre("SOPA PESCADO");  db.addGenero(aux); 
    	 aux.setId(321); aux.setNombre("CHIRLA");  db.addGenero(aux); 
    	 aux.setId(322); aux.setNombre("MERLUZA DE PINCHO 9P");  db.addGenero(aux); 
    	 aux.setId(323); aux.setNombre("LANGOSTA COCIDA");  db.addGenero(aux); 
    	 aux.setId(324); aux.setNombre("GASTOS DE ENVÍO");  db.addGenero(aux); 
    	 aux.setId(76); aux.setNombre("MEDIANA DE PINCHO 10P");  db.addGenero(aux); 
    	 aux.setId(86); aux.setNombre("BACALAO CONGELADO");  db.addGenero(aux); 
    	 aux.setId(87); aux.setNombre("BACALAO FRESCO C. C.");  db.addGenero(aux); 
    	 aux.setId(88); aux.setNombre("BERBERECHOS");  db.addGenero(aux); 
    	 aux.setId(89); aux.setNombre("BESUGO");  db.addGenero(aux); 
    	 aux.setId(91); aux.setNombre("CABEZA DE SAPO");  db.addGenero(aux); 
    	 aux.setId(92); aux.setNombre("CAMARON");  db.addGenero(aux); 
    	 aux.setId(93); aux.setNombre("CANGREJOS DE RIO");  db.addGenero(aux); 
    	 aux.setId(94); aux.setNombre("CARIOCA ANZUELO");  db.addGenero(aux); 
    	 aux.setId(95); aux.setNombre("CARIOCA DE PINCHO ");  db.addGenero(aux); 
    	 aux.setId(96); aux.setNombre("CARIOQUILLA ANZUELO");  db.addGenero(aux); 
    	 aux.setId(97); aux.setNombre("CARIOQUILLA DE ARRASTRE");  db.addGenero(aux); 
    	 aux.setId(98); aux.setNombre("CENTOLLO");  db.addGenero(aux); 
    	 aux.setId(99); aux.setNombre("CHAUCHA");  db.addGenero(aux); 
    	 aux.setId(100); aux.setNombre("CIGALA PEQUEÍA FRESCA");  db.addGenero(aux); 
    	 aux.setId(101); aux.setNombre("CIGALA GRANDE CONGELADA");  db.addGenero(aux); 
    	 aux.setId(103); aux.setNombre("CIGALA GRANDE  FRESCA");  db.addGenero(aux); 
    	 aux.setId(104); aux.setNombre("COLAS DE SAPOS");  db.addGenero(aux); 
    	 aux.setId(105); aux.setNombre("COLAS DE LANGOSTA CONGELADA");  db.addGenero(aux); 
    	 aux.setId(106); aux.setNombre("COLAS DE MERLUZA");  db.addGenero(aux); 
    	 aux.setId(107); aux.setNombre("COLORADOS");  db.addGenero(aux); 
    	 aux.setId(108); aux.setNombre("CONGRIO ");  db.addGenero(aux); 
    	 aux.setId(109); aux.setNombre("DORADA");  db.addGenero(aux); 
    	 aux.setId(110); aux.setNombre("ERLA ");  db.addGenero(aux); 
    	 aux.setId(111); aux.setNombre("FANECA");  db.addGenero(aux); 
    	 aux.setId(112); aux.setNombre("FILETES DE BACALAO FRESCO");  db.addGenero(aux); 
    	 aux.setId(113); aux.setNombre("FILETES DE LIBA");  db.addGenero(aux); 
    	 aux.setId(114); aux.setNombre("GALLITO");  db.addGenero(aux); 
    	 aux.setId(115); aux.setNombre("GALLO");  db.addGenero(aux); 
    	 aux.setId(116); aux.setNombre("GAMBA MEDIANA");  db.addGenero(aux); 
    	 aux.setId(117); aux.setNombre("GAMBA PLACHA");  db.addGenero(aux); 
    	 aux.setId(118); aux.setNombre("GAMBA SOPA");  db.addGenero(aux); 
    	 aux.setId(119); aux.setNombre("GAMBA PELADA CONGELADA");  db.addGenero(aux); 
    	 aux.setId(120); aux.setNombre("GAMBON GRANDE");  db.addGenero(aux); 
    	 aux.setId(121); aux.setNombre("GAMBON PEQUEÑOO");  db.addGenero(aux); 
    	 aux.setId(122); aux.setNombre("GULAS");  db.addGenero(aux); 
    	 aux.setId(123); aux.setNombre("KARRAKELAS");  db.addGenero(aux); 
    	 aux.setId(124); aux.setNombre("KISKILLA");  db.addGenero(aux); 
    	 aux.setId(126); aux.setNombre("KOKOCHAS DE MERLUZA FRESCA");  db.addGenero(aux); 
    	 aux.setId(127); aux.setNombre("KOKOCHAS DE BACALAO ");  db.addGenero(aux); 
    	 aux.setId(128); aux.setNombre("KOKOCHAS DE MERLUZA CONGELADA");  db.addGenero(aux); 
    	 aux.setId(129); aux.setNombre("KRABA");  db.addGenero(aux); 
    	 aux.setId(130); aux.setNombre("KRABARROCA");  db.addGenero(aux); 
    	 aux.setId(131); aux.setNombre("LAMOTE");  db.addGenero(aux); 
    	 aux.setId(132); aux.setNombre("LANGOSTA");  db.addGenero(aux); 
    	 aux.setId(133); aux.setNombre("LANGOSTA CONGELADA");  db.addGenero(aux); 
    	 aux.setId(134); aux.setNombre("LANGOSTINOS GRADES");  db.addGenero(aux); 
    	 aux.setId(135); aux.setNombre("LANGOSTINOS PEQUEÑOS");  db.addGenero(aux); 
    	 aux.setId(136); aux.setNombre("LANGOSTINOS GRANDES CONGELADOS");  db.addGenero(aux); 
    	 aux.setId(137); aux.setNombre("LANGOSTINOS PEQUEÑOS CONGELADOS");  db.addGenero(aux); 
    	 aux.setId(138); aux.setNombre("LANGOSTINOS TIGRE PEQUEÑOS");  db.addGenero(aux); 
    	 aux.setId(139); aux.setNombre("LANGOSTINOS TIGRE GRANDES");  db.addGenero(aux); 
    	 aux.setId(140); aux.setNombre("LENGUADITO");  db.addGenero(aux); 
    	 aux.setId(141); aux.setNombre("LENGUADO");  db.addGenero(aux); 
    	 aux.setId(142); aux.setNombre("LIBA");  db.addGenero(aux); 
    	 aux.setId(143); aux.setNombre("LOCHA");  db.addGenero(aux); 
    	 aux.setId(144); aux.setNombre("LOCHO");  db.addGenero(aux); 
    	 aux.setId(145); aux.setNombre("LUBINA");  db.addGenero(aux); 
    	 aux.setId(146); aux.setNombre("MERO");  db.addGenero(aux); 
    	 aux.setId(147); aux.setNombre("MARUCO");  db.addGenero(aux); 
    	 aux.setId(148); aux.setNombre("MEDIANA ANZUELO");  db.addGenero(aux); 
    	 aux.setId(149); aux.setNombre("MEDIANA DE ARRASTRE");  db.addGenero(aux); 
    	 aux.setId(150); aux.setNombre("MEDIANILLA DE ARRASTRE");  db.addGenero(aux); 
    	 aux.setId(151); aux.setNombre("MEDIANILLA ANZUELO");  db.addGenero(aux); 
    	 aux.setId(152); aux.setNombre("MEJILLON GRANDE");  db.addGenero(aux); 
    	 aux.setId(153); aux.setNombre("MEJILLON PEQUEÑO");  db.addGenero(aux); 
    	 aux.setId(154); aux.setNombre("MERLUZA ANZUELO");  db.addGenero(aux); 
    	 aux.setId(155); aux.setNombre("MERLUZA DE ARRASTRE");  db.addGenero(aux); 
    	 aux.setId(156); aux.setNombre("MISERA CANADIENSE");  db.addGenero(aux); 
    	 aux.setId(157); aux.setNombre("MUELAS DE TXAGURRO");  db.addGenero(aux); 
    	 aux.setId(158); aux.setNombre("MUXARRA");  db.addGenero(aux); 
    	 aux.setId(159); aux.setNombre("MUXUMARTIN");  db.addGenero(aux); 
    	 aux.setId(160); aux.setNombre("NECORAS");  db.addGenero(aux); 
    	 aux.setId(161); aux.setNombre("OSTRAS");  db.addGenero(aux); 
    	 aux.setId(162); aux.setNombre("PANCHOS");  db.addGenero(aux); 
    	 aux.setId(163); aux.setNombre("PERCEBES");  db.addGenero(aux); 
    	 aux.setId(164); aux.setNombre("PERLITA");  db.addGenero(aux); 
    	 aux.setId(165); aux.setNombre("PERLON");  db.addGenero(aux); 
    	 aux.setId(166); aux.setNombre("PESCADILLA ANZUELO");  db.addGenero(aux); 
    	 aux.setId(167); aux.setNombre("PESCADILLA DE ARRASTRE ");  db.addGenero(aux); 
    	 aux.setId(168); aux.setNombre("PITILLO ANZUELO");  db.addGenero(aux); 
    	 aux.setId(169); aux.setNombre("PITILLO DE ARRASTRE ");  db.addGenero(aux); 
    	 aux.setId(170); aux.setNombre("PLATUSA RUBIA");  db.addGenero(aux); 
    	 aux.setId(171); aux.setNombre("PULPO COCIDO");  db.addGenero(aux); 
    	 aux.setId(172); aux.setNombre("PULPO FRESCO");  db.addGenero(aux); 
    	 aux.setId(173); aux.setNombre("RANAS");  db.addGenero(aux); 
    	 aux.setId(174); aux.setNombre("RAYA");  db.addGenero(aux); 
    	 aux.setId(175); aux.setNombre("RELOJES ");  db.addGenero(aux); 
    	 aux.setId(176); aux.setNombre("RELOJITOS");  db.addGenero(aux); 
    	 aux.setId(177); aux.setNombre("REY");  db.addGenero(aux); 
    	 aux.setId(178); aux.setNombre("RODABALLO");  db.addGenero(aux); 
    	 aux.setId(179); aux.setNombre("SALMON");  db.addGenero(aux); 
    	 aux.setId(180); aux.setNombre("SALMON AHUMADO");  db.addGenero(aux); 
    	 aux.setId(181); aux.setNombre("SALMONETE PEQUEÑO");  db.addGenero(aux); 
    	 aux.setId(182); aux.setNombre("SALMONETE GRANDE");  db.addGenero(aux); 
    	 aux.setId(183); aux.setNombre("SAPITO");  db.addGenero(aux); 
    	 aux.setId(184); aux.setNombre("SAPO");  db.addGenero(aux); 
    	 aux.setId(185); aux.setNombre("SARDINA");  db.addGenero(aux); 
    	 aux.setId(186); aux.setNombre("SAVIRON ");  db.addGenero(aux); 
    	 aux.setId(187); aux.setNombre("SOLDADITOS");  db.addGenero(aux); 
    	 aux.setId(188); aux.setNombre("SUELAS");  db.addGenero(aux); 
    	 aux.setId(190); aux.setNombre("TRUCHA");  db.addGenero(aux); 
    	 aux.setId(191); aux.setNombre("TXAKA ");  db.addGenero(aux); 
    	 aux.setId(192); aux.setNombre("TXANGURRO");  db.addGenero(aux); 
    	 aux.setId(197); aux.setNombre("TXITXARRO");  db.addGenero(aux); 
    	 aux.setId(198); aux.setNombre("VERDEL");  db.addGenero(aux); 
    	 aux.setId(199); aux.setNombre("DAVID");  db.addGenero(aux); 
    	 aux.setId(200); aux.setNombre("HUEVAS");  db.addGenero(aux); 
    	 aux.setId(201); aux.setNombre("PALOMETA");  db.addGenero(aux); 
    	 aux.setId(203); aux.setNombre("TINTAS");  db.addGenero(aux); 
    	 aux.setId(204); aux.setNombre("PESCADILLA CORTA");  db.addGenero(aux); 
    	 aux.setId(205); aux.setNombre("PESCADILLA LARGA");  db.addGenero(aux); 
    	 aux.setId(207); aux.setNombre("CALAMAR PEQUEÑO CONGELADO");  db.addGenero(aux); 
    	 aux.setId(208); aux.setNombre("MEDIANILLA DE PINCHO");  db.addGenero(aux); 
    	 aux.setId(209); aux.setNombre("PESCADILLA DE PINCHO");  db.addGenero(aux); 
    	 aux.setId(210); aux.setNombre("PUTXANO");  db.addGenero(aux); 
    	 aux.setId(211); aux.setNombre("FLETAN");  db.addGenero(aux); 
    	 aux.setId(214); aux.setNombre("CARRILLERA DE SAPO");  db.addGenero(aux); 
    	 aux.setId(216); aux.setNombre("PESCADILLA CERRADA");  db.addGenero(aux); 
    	 aux.setId(217); aux.setNombre("ATUN");  db.addGenero(aux); 
    	 aux.setId(218); aux.setNombre("CARRILLERAS DE BACALAO");  db.addGenero(aux); 
    	 aux.setId(219); aux.setNombre("TXAKA BARRITAS");  db.addGenero(aux); 
    	 aux.setId(220); aux.setNombre("TXAKA RALLADO");  db.addGenero(aux); 
    	 aux.setId(223); aux.setNombre("LANGOSTINO COCIDO PEQUEÑO");  db.addGenero(aux); 
    	 aux.setId(224); aux.setNombre("LANGOSTINO COCIDO GRANDE");  db.addGenero(aux); 
    	 aux.setId(226); aux.setNombre("BONITO");  db.addGenero(aux); 
    	 aux.setId(227); aux.setNombre("ABADEJO");  db.addGenero(aux); 
    	 aux.setId(228); aux.setNombre("ALIGOTE");  db.addGenero(aux); 
    	 aux.setId(229); aux.setNombre("ALMEJA LIMON");  db.addGenero(aux); 
    	 aux.setId(230); aux.setNombre("ALMEJA SOPA");  db.addGenero(aux); 
    	 aux.setId(231); aux.setNombre("ALMEJA TERCIADA");  db.addGenero(aux); 
    	 aux.setId(232); aux.setNombre("ALMEJA MARINERA");  db.addGenero(aux); 
    	 aux.setId(233); aux.setNombre("ANGULAS");  db.addGenero(aux); 
    	 aux.setId(234); aux.setNombre("ANTXOA");  db.addGenero(aux); 
    	 aux.setId(235); aux.setNombre("ANTXOILLA");  db.addGenero(aux); 
    	 aux.setId(236); aux.setNombre("ANCHOILLA DE PINCHO");  db.addGenero(aux); 
    	 aux.setId(237); aux.setNombre("CAJA GRANDE");  db.addGenero(aux); 
    	 aux.setId(238); aux.setNombre("CAJA PEQUEÑA");  db.addGenero(aux); 
    	 aux.setId(239); aux.setNombre("BACALAO FRESCO S. C.");  db.addGenero(aux); 
    	 aux.setId(240); aux.setNombre("COQUINAS");  db.addGenero(aux); 
    	 aux.setId(241); aux.setNombre("MERLUZA DE PINCHO 5P");  db.addGenero(aux); 
    	 aux.setId(242); aux.setNombre("PULPO COCIDO PATAS");  db.addGenero(aux); 
    	 aux.setId(243); aux.setNombre("ALMEJA CULTIVO");  db.addGenero(aux); 
    	 aux.setId(244); aux.setNombre("CAJA PEQUEÑA COFRADIA");  db.addGenero(aux); 
    	 aux.setId(245); aux.setNombre("VARIADO");  db.addGenero(aux); 
    	 aux.setId(246); aux.setNombre("GATOS");  db.addGenero(aux); 
    	 aux.setId(247); aux.setNombre("NAVAJAS");  db.addGenero(aux); 
    	 aux.setId(248); aux.setNombre("PESCADILLA  ABIERTA");  db.addGenero(aux); 
    	 aux.setId(249); aux.setNombre("ANDEJA");  db.addGenero(aux); 
    	 aux.setId(250); aux.setNombre("BACALAO SALADO");  db.addGenero(aux); 
    	 aux.setId(251); aux.setNombre("VIEIRAS");  db.addGenero(aux); 
    	 aux.setId(252); aux.setNombre("TXANGURRO DESMIGADO");  db.addGenero(aux); 
    	 aux.setId(253); aux.setNombre("LANGOSTINOS SALVAJES");  db.addGenero(aux); 
    	 aux.setId(254); aux.setNombre("ZAMBURRIÑAS");  db.addGenero(aux); 
    	 aux.setId(255); aux.setNombre("CERRADO");  db.addGenero(aux); 
    	 aux.setId(256); aux.setNombre("CARABINEROS");  db.addGenero(aux); 
    	 aux.setId(257); aux.setNombre("PERCEBE COCIDO CAJA AL VACÍO");  db.addGenero(aux); 
    	 aux.setId(258); aux.setNombre("MISERA DEL PAIS");  db.addGenero(aux); 
    	 aux.setId(259); aux.setNombre("CALAMAR GRANDE");  db.addGenero(aux); 
    	 aux.setId(260); aux.setNombre("CALAMAR PEQUEÑO");  db.addGenero(aux); 
    	 aux.setId(261); aux.setNombre("CALAMAR TERCIADO");  db.addGenero(aux); 
    	 aux.setId(262); aux.setNombre("CALAMAR ANZUELO");  db.addGenero(aux); 
    	 aux.setId(263); aux.setNombre("CALAMAR GRANDE CONGELADO");  db.addGenero(aux); 
    	 aux.setId(264); aux.setNombre("PLATUSA NEGRA");  db.addGenero(aux); 
    	 aux.setId(265); aux.setNombre("TXITXARRILLO");  db.addGenero(aux); 
    	 aux.setId(266); aux.setNombre("CAJA  COFRADIA PEQUEÑA");  db.addGenero(aux); 
 


    }
    

    
    public void fBtSig()
    {
    	
    	boolean error=false;
    	//Compra
    	miCompra=new Compra();
    	
        //Lista para las compras
        this.Compras = new ArrayList<DetalleCompras>();
        
       if (this.spProv.getSelectedItemPosition()==0 || this.spGenero.getSelectedItemPosition()==0 || cantidad.getText().toString().equals("")
    	|| precio.getText().toString().equals("") || fecha.getText().toString().equals(""))    	   
    	   error=true;
        
       if (!error)
       {
    	   MySQLiteHelper db = new MySQLiteHelper(this);
          	
          	
       	clases.Configuracion conf =db.getConf(1);
       	miCompra.setIdProveedor(((proveedor)this.spProv.getSelectedItem()).getId());
	        miCompra.setCif(((proveedor)this.spProv.getSelectedItem()).getCif());
	        miCompra.setFecha(fecha.getText().toString());
	        miCompra.setImpuestos(conf.getImpuestos());
	        miCompra.setIva(conf.getIva());
	        
	        DetalleCompras aux= new DetalleCompras();
	        aux.setCantidad(Double.parseDouble(cantidad.getText().toString()));
	        aux.setPrecio(Double.parseDouble(precio.getText().toString()));
	        aux.setIdGenero(((genero)spGenero.getSelectedItem()).getId());
	        Compras.add(aux);
	        
	        proveedor=((proveedor)this.spProv.getSelectedItem()).getNombre();
	        
	    	Intent intent = new Intent(this, AddCompra.class);
	    	intent.putExtra("proveedor", proveedor);
	    	intent.putExtra("compra", miCompra);
	    	intent.putExtra("detalle", Compras);
	        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        startActivity(intent); 
       }
       else
       {
    	   Toast.makeText(getApplicationContext(), "Rellene los datos obligatorios", Toast.LENGTH_SHORT).show();
    	   
       }
    }
    
    public void fBtGuardar()
    {
    	
    	boolean error=false;
    	
    	//Compra
    	miCompra=new Compra();
        //Lista para las compras
        this.Compras = new ArrayList<DetalleCompras>();
        
        
        
        if (this.spProv.getSelectedItemPosition()==0 || this.spGenero.getSelectedItemPosition()==0 || cantidad.getText().toString().equals("")
            	|| precio.getText().toString().equals("") || fecha.getText().toString().equals(""))    	   
            	   error=true;
        
       if (!error) 
       {
    	   MySQLiteHelper db = new MySQLiteHelper(this);
       	
       	
       		clases.Configuracion conf =db.getConf(1);
       		
       		miCompra.setIdProveedor(((proveedor)this.spProv.getSelectedItem()).getId());
	        miCompra.setCif(((proveedor)this.spProv.getSelectedItem()).getCif());
	        miCompra.setFecha(fecha.getText().toString());
	        miCompra.setImpuestos(conf.getImpuestos());
	        miCompra.setIva(conf.getIva());
	        
	        DetalleCompras aux= new DetalleCompras();
	        aux.setCantidad(Double.parseDouble(cantidad.getText().toString()));
	        aux.setPrecio(Double.parseDouble(precio.getText().toString()));
	        aux.setIdGenero(((genero)spGenero.getSelectedItem()).getId());
	        Compras.add(aux);
	        
	        proveedor=((proveedor)this.spProv.getSelectedItem()).getNombre();
	        	
	    	Intent intent = new Intent(this, ConfirmaCompra.class);
	    	//intent.putExtra("proveedor", proveedor);
	    	intent.putExtra("compra", miCompra);
	    	intent.putExtra("detalle", Compras);
	        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        startActivity(intent); 
       }
       else
       {
    	   Toast.makeText(getApplicationContext(), "Rellene los datos obligatorios", Toast.LENGTH_SHORT).show();
       }
    }
    /**
     * Function to load the spinner data from SQLite database
     * */
    public void loadSpinnerData() {
        // database handler
    	 MySQLiteHelper db = new MySQLiteHelper(this);
 
    	 
    	db.getAllDetComp();
        // Spinner Drop down elements
        List<proveedor> lables = db.getAllProvs();
        lables.add(0,new proveedor());
 
        // Creating adapter for spinner
        ArrayAdapter<proveedor> dataAdapter = new ArrayAdapter<proveedor>(this,
                android.R.layout.simple_spinner_item, lables);
 
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        // attaching data adapter to spinner
        spProv.setAdapter(dataAdapter);
        
        
        //------------------------------
        
     // Spinner Drop down elements
        List<genero> lables1 = db.getAllGens();
        lables1.add(0,new genero());
 
        // Creating adapter for spinner
        ArrayAdapter<genero> dataAdapter1 = new ArrayAdapter<genero>(this,android.R.layout.simple_spinner_item, lables1);
 
        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        // attaching data adapter to spinner
        spGenero.setAdapter(dataAdapter1);
        
    }
    

    @SuppressLint("SimpleDateFormat")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            
        main=this; 
        //genero();
        //proveedores();
        this.setTitle("Comprar");
        
        mTitle =  getTitle();
        mOptions = getResources().getStringArray(R.array.options_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        
        

        
        //Asocio xml a java
        this.btSig = (Button) findViewById(R.id.btSigAdd);
        this.btGuardar = (Button) findViewById(R.id.btguardar);
        this.spProv = (Spinner) findViewById(R.id.spinner1);
        this.spGenero = (Spinner) findViewById(R.id.spGenero);
        this.cantidad = (EditText) findViewById(R.id.txtCant);
        this.precio = (EditText) findViewById(R.id.txtPrecio);
        this.fecha = (EditText) findViewById(R.id.txtFecha);
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        fecha.setText(dateFormat.format(date));
        
        loadSpinnerData();
        
        btSig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            		fBtSig();
            }
        });
        
        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            		fBtGuardar();
            }
        });
        
        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        CustomList adapter = new CustomList(MainActivity.this, mOptions, imageId);
            
        mDrawerList.setAdapter(adapter); 
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.action_settings,  /* "open drawer" description for accessibility */
                R.string.proveedores  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                //getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                //getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        
     // Inflate the menu; this adds items to the action bar if it is present.
     		getMenuInflater().inflate(R.menu.main, menu);
     	
     		return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         // The action bar home/up action should open or close the drawer.
         // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch(item.getItemId()) {

        default:
            return super.onOptionsItemSelected(item);
        }
    }

    /* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
        // update selected item and title, then close the drawer
       
        Intent intent;
        switch (position) {
        case 0:
            
        break;
        case 1:
        	intent = new Intent(this, Compras.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        break; 
        case 2:
        	intent = new Intent(this, Descargar.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        break; 
        case 3:
        	intent = new Intent(this, Proveedores.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        break; 
        case 4:
        	intent = new Intent(this, com.app.pescaderiaespe.Genero.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        break; 
        case 5:
        	intent = new Intent(this, Configuracion.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        break;

        }
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

}
