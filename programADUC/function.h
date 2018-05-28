#ifndef _FUNCTION_H_
#define _FUNCTION_H_

#include <aduc831.h>
#include <stdarg.h>
#include <stdio.h>
#include <string.h>



#define SetBit(Byte, Bit) Byte|=(0x01<<Bit)
#define ClrBit(Byte, Bit) (Byte&=~(0x01<<Bit))
#define ToggleBit(Byte, Bit) Byte^=(0x01<<Bit)
#define CheckBit(Byte, Bit) (Byte&(0x01<<Bit))

#define T1_dzielnik 12
#define T1_rozdzielczosc 16
#define T0_dzielnik 12
#define T0_rozdzielczosc 16
#define F_OSC 11058000.0
#define Tx_tau(dzielnik) (float)((float)dzielnik / F_OSC) // obliczenie okresu zegara ukladu licznikowego
#define Tx_N(czas_ms, dzielnik) (unsigned int)((float)czas_ms / Tx_tau(dzielnik) /1000) //obliczanie ilosci impulsów  

#define T1_rejestr_wartosc_poczatkowa(czas_ms) ((0x000001ul<<T1_rozdzielczosc) - Tx_N(czas_ms, T1_dzielnik))
#define T1_ustaw_czas_ms(czas_ms) TL1=T1_rejestr_wartosc_poczatkowa(czas_ms); TH1=T1_rejestr_wartosc_poczatkowa(czas_ms)>>8
#define T0_rejestr_wartosc_poczatkowa(czas_ms) ((0x000001ul<<T0_rozdzielczosc) - Tx_N(czas_ms, T0_dzielnik))
#define T0_ustaw_czas_ms(czas_ms) TL0=T0_rejestr_wartosc_poczatkowa(czas_ms); TH0=T0_rejestr_wartosc_poczatkowa(czas_ms)>>8

#define LedPort P2 
#define LED_D5 7
#define LED_D9 6
#define LED_D11 5
#define LED_D12 4
#define TRIAK 1

#define LedOn(Dioda) ClrBit(LedPort,Dioda)
#define LedOff(Dioda) SetBit(LedPort,Dioda)
#define Triak_On()  ClrBit(LedPort,TRIAK)
#define Triak_Off() 	SetBit(LedPort,TRIAK)
#define liczczas(alfa) (20.0*(float)alfa/(float)360.0)

#define LCDRS MCO  
#define LCDRW MDO
#define LCDDANE P0 

typedef signed char int8_t;
typedef unsigned char uint8_t;
typedef signed int int16_t;
typedef unsigned int uint16_t;
typedef signed long int int32_t;
typedef unsigned long int uint32_t;
typedef float float32_t;
sbit LCDZgoda = P2^0; 

extern xdata unsigned char TRANSLATED_MORS[50];
extern xdata unsigned char *WSK_TRANSLATED_MORS;
extern xdata unsigned char mors[][5];

void LCD_GoTo(unsigned char x,unsigned char y);
void LCD_Send(char dane, bit RS, bit RW);
void LCD_znak(char znak);
void LCD_Send_str(char *str);
void Function_set(int D, int N,int F);

void Display_ON_OFF(int D, int C,int B);
void Display_clear(void);
void Entry_mode(int I, int S );
void LCD_init(void);
void delay(unsigned long c);
void delay_ms(int ms);
void delay_us(int us);
void UART_init(void);
//void UART_printf(const char * format,...);
void UART_puts(char* str);
char UART_putchar (char c);

void UART_SEND_MORSE(char* str);
void ARRAY_clear(char* str);
void MORS_trans( char *tab);
void LCD_Display_Cursor_shift(int S, int R);
//void MORS_signal();
#endif