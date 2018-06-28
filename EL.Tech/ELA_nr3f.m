%Matlab/Octave initialisieren:
clear all; clc; format compact; format short g;
%  Parameter:
f_0=0; f_E=10^7; N=10^6; lw=3;
R_1=820;
R_i=50;
C_1=2.2e-6; 
U=12;
%Funktion:
g=@(f)U./(R_i+R_1+1./(1i*2*pi*f*C_1));
%daten:
f_data=linspace(f_0,f_E,N);
g_data=g(f_data);
%plot:
figure(6);
subplot(2,1,1);
plot(f_data,abs(g_data),'linewidth',lw); %oder semilogy oder loglog oder semilogx
xlabel('f[Hz]');
ylabel('A')
grid on;
subplot(2,1,2);
semilogx(f_data,angle(g_data)*180/pi,'linewidth',lw);
xlabel('f[Hz]');
ylabel('\phi[\pi]');
grid on;