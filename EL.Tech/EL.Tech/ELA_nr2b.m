clear all; clc; format compact; format short g;
%parameter
R=820;
C1=2.2e-6;
C2=47e-9;
dt=1e-1;
f_0=10;
f_E=1e6;
N=1e6;
lw=3;
%Funktionen
g=@(f)(1i*2*pi*f*R*C1)./(1+1i*2*pi*f*R*C1);
% daten
f_data=linspace(f_0,f_E,N);
g_data=g(f_data);
%exp data
f_exp_Data=[10,30,100,300,1000,3000,10000,30000,100000,300000];
second_u_Data=[0.05,0.15,0.35,0.4,0.5,0.5,0.5,0.5,0.5,0.5];
second_phi_Data=[-4/9,-7/18,-2/9,-1/10,-1/30,0,0,0,0,0];
%PLOT
figure(3);
subplot(2,1,1);
semilogx(f_data,20*log(abs(g_data))/log(10),'linewidth',lw);
hold on;
semilogx(f_exp_Data,20*log(second_u_Data*2)/log(10),'linewidth',lw);
hold off;
xlabel('f[Hz]');ylabel('|G|[dB]');
title('Amplitudengang |G(f)|')
subplot(2,1,2);
semilogx(f_data,angle(g_data)/pi,'linewidth',lw);
hold on;
semilogx(f_exp_Data,-1*second_phi_Data,'linewidth',lw);
hold off;
xlabel('f[Hz]');
ylabel('\phi[\pi]');
title('Phasengang in rad [\pi]');
grid on;