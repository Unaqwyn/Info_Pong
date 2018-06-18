clear all; clc; format compact; format short g;
%parameter
R=2700;
C=10e-9;
Rl=1000;
dt=1e-1
f_0=10;
f_E=1e6;
N=1e6;
lw=3;
%f_0=0; f_E=10^5; N=10^5; lw=3;
%Funktionen
%g=@(f)0.5*f.*exp(j*2*pi*f/10^5);
g=@(f)1./(1+2*pi*f*1i*C*R);
% daten
f_data=linspace(f_0,f_E,N);
g_data=g(f_data);
%PLOT
figure(1);
subplot(2,1,1);
semilogx(f_data,20*log(abs(g_data))/log(10),'linewidth',lw);
xlabel('f[Hz]');ylabel('A[dB]');
grid on;
subplot(2,1,2);
semilogx(f_data,angle(g_data)/pi,'linewidth',lw);
xlabel('f[Hz]');ylabel('\phi[\pi]');
grid on;