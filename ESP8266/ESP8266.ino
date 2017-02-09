#include <ESP8266WiFi.h>
#include <WiFiClient.h>

const char* ssid = "rede3gwifi";//nome da rede
const char* senha = "rede3gwifi";//senha da rede
const char* host = "192.168.100.101";//IP onde o servidor está rodando

WiFiClient client;

void setup() {

  delay(2000);
  Serial.begin(9600);

  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, senha);

  Serial.print("Conectando na rede Rede: ");
  Serial.println(ssid);

  while (WiFi.status() != WL_CONNECTED) {

    delay(1000);
    Serial.print(".");

  }

  delay(500);
  //Serial.println();
  Serial.print("My IP: ");
  Serial.println(WiFi.localIP());

  while (!client.connect(host, 3000)) {

    delay(1000);
    Serial.println("Não conectado ao servidor");

  }
  
}

void loop() {

  String disp = client.readStringUntil('\n'); //Receber informações do servidor
  Serial.println(disp);

  client.println("Olá Servidor!!");
  delay(1000);

}

